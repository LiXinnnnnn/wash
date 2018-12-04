package com.laiba.wash.front.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.laiba.wash.core.po.Operator;
import com.laiba.wash.core.service.OperatorService;
import com.laiba.wash.front.constant.FrontContant;
import com.laiba.wash.front.util.MD5Util;

@Controller
@RequestMapping("/operator")
public class OperatorController {
	
	@Autowired
	private OperatorService operatorService;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Producer captchaProducer;	
	
	@RequestMapping("/noAuthority.htm")
	public String noAuthority(){		
		return "/control/noAuthority";
	}
	
	/**
	 * 
	 * @param path
	 * @param session
	 * @return
	 */
	@RequestMapping("/login.htm")
	public String loginPage(String page, HttpSession session) {
		if (!StringUtils.isBlank(page)) {
			session.setAttribute(FrontContant.SESSION_PATH, page); 
		}
		return "/operator/login";
	}
	/**
	 * 登录验证
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/ajax/loginSubmit.htm")
	public String loginSubmit(String loginName,String password,ModelMap map,HttpSession session, String kaptcha) throws Exception {
		try {
			//验证码
			/*String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			if (!kaptcha.equals(code)) {
				map.addAttribute("result", "{'success':'false','msg':'验证码错误！'}");
				return "/control/blank";
			}*/
			//参数判断
			if(StringUtils.isBlank(loginName) || StringUtils.isBlank(password)){
				map.addAttribute("result", "{'success':'false','msg':'用户名或密码不为空！'}");
				return "/control/blank";
			}
			//登录错误
			Operator operatorModel = operatorService.login(loginName, MD5Util.encryptMD5(password));
			if (operatorModel == null) {
				map.addAttribute("result", "{'success':'false','msg':'用户名或密码错误！'}");
				return "/control/blank";
			}
			session.setAttribute(FrontContant.SESSION_OPERATOR,operatorModel);
			String path = (String) session.getAttribute(FrontContant.SESSION_PATH);
			
			map.addAttribute("result", "{'success':'true','msg':'','path':'"+path+"'}");
			log.info(loginName + "登录成功");
			
			return "/control/blank";
			
		} catch (Exception e) {
			log.error("exception:",e);
			map.addAttribute("result", "{'success':'false','msg':'服务器异常！'}");
			return "/control/blank";
		}
	}
	/**
	 * 修改密码页面
	 * @return
	 */
	@RequestMapping("/updatePasswordPage.htm")
	public String updatePasswordPage(){
		return "/operator/updatePassword";
	}
	/**
	 * 修改密码操作
	 * @param oldPassword
	 * @param newPassword
	 * @param map
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/ajax/updatePassword.htm")
	public String updatePasswordPage(String oldPassword,String newPassword,ModelMap map,HttpSession session) throws Exception{
		//参数验证
		if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
			 map.addAttribute("result", "{'success':'false','msg':'修改失败！'}");
				return "/control/blank";
		}
		 //旧密码是否正确
		Operator operator = (Operator) session.getAttribute(FrontContant.SESSION_OPERATOR);
		 if (!MD5Util.encryptMD5(oldPassword).equals(operator.getPassword())) {
			 map.addAttribute("result", "{'success':'false','msg':'旧密码错误！'}");
				return "/control/blank";
		}
		//修改密码
		int i = operatorService.updatePassword(operator.getId(),MD5Util.encryptMD5(newPassword));
		if (i > 0) {
			session.invalidate();
			map.addAttribute("result", "{'success':'true','msg':'修改成功！请重新登陆！'}");			
		}else {
			map.addAttribute("result", "{'success':'false','msg':'修改失败！'}");
		}
		return "/control/blank";
	}
	/**
	 * 登出
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.htm")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/operator/login.htm";
	}
	
	/**
	 * 刷新验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/login/kaptcha.htm")
	public void getKaptchaImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		try {
			response.setDateHeader("Expires", 0);
			
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			
			// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			
			// Set standard HTTP/1.0 no-cache header.
			response.setHeader("Pragma", "no-cache");
			
			// return a jpeg
			response.setContentType("image/jpeg");
			
			// create the text for the image
			String capText = captchaProducer.createText();
			
			// store the text in the session
			HttpSession session = request.getSession();
			session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
			
			// create the image with the text
			BufferedImage bi = captchaProducer.createImage(capText);
			ServletOutputStream out = response.getOutputStream();
			
			// write the data out
			ImageIO.write(bi, "jpg", out);
			try {
				out.flush();
			} finally {
				out.close();
			}
			
		} catch (Exception e) {
			//donothing
		}
	}	
	
	
}
