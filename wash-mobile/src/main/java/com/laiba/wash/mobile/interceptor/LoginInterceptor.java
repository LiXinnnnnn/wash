package com.laiba.wash.mobile.interceptor;

import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.laiba.wash.core.po.User;
import com.laiba.wash.core.po.WxAccessToken;
import com.laiba.wash.core.service.UserService;
import com.laiba.wash.mobile.constant.FrontContant;
import com.laiba.wash.mobile.front.dto.SNSUserInfo;
import com.laiba.wash.mobile.util.WeChatWebManager;

/**
 * 权限控制
 * 
 * @author jerry
 *
 */
public class LoginInterceptor implements HandlerInterceptor, InitializingBean {

	private Resource resource;

	private Properties properties = new Properties();

	private static Logger logger = LoggerFactory
			.getLogger(LoginInterceptor.class);

	@Autowired
	private UserService userService;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		String query = request.getQueryString();
		
		if (url.contains("test.htm") || url.contains("wxpayReport.htm")) {
			return true;
		}
		// 如果session存在clientUser 不从微信获取用户信息
		User clientUserSession = (User) request.getSession().getAttribute(
				FrontContant.SESSION_OPERATOR);
		if (clientUserSession != null) {
			return true;
		}
		//开发专用
		String openId = request.getParameter("openId");
		if (StringUtils.isNotBlank(openId)) {
			User user = userService.getUserByOpenId(openId);
			if (user != null) {
				request.getSession().setAttribute(FrontContant.SESSION_OPERATOR, user);
				return true;
			}
		}
		
		String code = request.getParameter("code");
		logger.info("------code------" + code);
		if (StringUtils.isEmpty(code)) {
            response.sendRedirect(WeChatWebManager.getWeChatCodeUrl("snsapi_base", url, query, "base"));
            return false;
        }

		// 获取accessToken
		openId = WeChatWebManager.getOpenIdByCode(code);
		User user = userService.getUserByOpenId(openId);
		if (user != null) {
			request.getSession().setAttribute(FrontContant.SESSION_OPERATOR, user);
			return true;
		}
		//获取access_token
		WxAccessToken accessToken = userService.getWxAccessToken();
		if (accessToken == null) {
			accessToken = WeChatWebManager.getAccessToken();
			//更新access_token 
			userService.updateAccessToken(accessToken);
		}
		
		//获取用户信息，保存到本地
		SNSUserInfo snsUserInfo = WeChatWebManager.getWeiXinUserInfo(accessToken.getAccessToken(), openId);
		user = new User();
		user.setOpenId(openId);
		user.setWxName(filterEmoji(snsUserInfo.getNickname()));
		if (StringUtils.isEmpty(user.getWxName())){
			user.setWxName("微信用户");
		}
		user.setWxImg(snsUserInfo.getHeadImgUrl());
		user.setSex(snsUserInfo.getSex());
		user.setProvince(snsUserInfo.getProvince());
		user.setCity(snsUserInfo.getCity());
		user.setCreateTime(new Date());
		userService.insertUser(user);
		request.getSession().setAttribute(FrontContant.SESSION_OPERATOR, user);
		return true;
	}



	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		properties.load(resource.getInputStream());
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 *
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (StringUtils.isBlank(source)) {
			return source;
		}
		StringBuilder buf = null;
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}
				buf.append(codePoint);
			}
		}
		if (buf == null) {
			return source;
		} else {
			if (buf.length() == len) {
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}


}
