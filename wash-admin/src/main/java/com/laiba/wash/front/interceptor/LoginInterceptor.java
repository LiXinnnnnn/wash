package com.laiba.wash.front.interceptor;

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.laiba.wash.core.enums.CommonVar;
import com.laiba.wash.core.po.Operator;
import com.laiba.wash.front.constant.FrontContant;

/**
 * 权限控制
 * 
 * @author jerry
 *
 */
public class LoginInterceptor implements HandlerInterceptor, InitializingBean {

	private Resource resource;

	private Properties properties = new Properties();

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		Operator operator = (Operator) session.getAttribute(FrontContant.SESSION_OPERATOR);
		String uri = request.getRequestURI();
		if (uri.contains("uploadify")) {
			return true;
		}
		if (operator != null) {
			// 用户已经登陆
			if ( CommonVar.SYSTEM_ADMIN_NAME.equals(operator.getName())) {
				// 用户有权限，放行。
				return true;
			} else {
				// 没有权限。
				/*
				 * response.sendRedirect(request.getContextPath() +
				 * "/operator/noAuthority.htm");
				 */
				response.sendRedirect(request.getContextPath()
						+ "/operator/noAuthority.htm");
				return false;
			}
		} else {
			// 用户未登录
			// 访问登录页面，直接放行
			if (isAllowLogin(request)) {
				return true;
			}
			// 不是登录页面，分情况处理
			
			if (uri.indexOf("ajax") != -1) {
				// ajax处理方式
				PrintWriter pw = response.getWriter();
				String ajax = "{'success':'timeout','msg':'登录超时，请重新登录。'}";
				pw.print(ajax);
				pw.flush();
				pw.close();
				return false;
			} else if (uri.indexOf("upload") != -1 || uri.indexOf("download") != -1) {
				response.sendRedirect(request.getContextPath() + "/operator/login.htm");
				return false;
			} else {
				// 用户想要登录的url
				String servletPath = request.getServletPath();
				
				String queryString = request.getQueryString();
				if (queryString != null && !queryString.equals("")) {
					servletPath += "?" + queryString;
				}
				session.setAttribute(FrontContant.SESSION_PATH, servletPath);
				response.sendRedirect(request.getContextPath() + "/operator/login.htm");
				return false;
			}
		}

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * 验证用户权限
	 * 
	 * @param url
	 * @param operatorUrl
	 * @return
	 */
	public boolean validatePermssion(String url, String operatorUrl) {

		if (FrontContant.FILTER_URL.indexOf(url) != -1 || url.indexOf(FrontContant.AJAX_URL) != -1 || url.indexOf("download") != -1|| url.indexOf("upload") != -1) {
			return true;
		}

		if (operatorUrl.indexOf(url) != -1) {
			return true;
		}
		return false;
	}

	/**
	 * 访问的是否是登录有关的路径
	 * 
	 * @return
	 */
	public boolean isAllowLogin(HttpServletRequest request) {
		String uri = request.getRequestURI();
		uri = uri.replace(request.getContextPath(), "");
		if (FrontContant.FILTER_URL.indexOf(uri) != -1 ) {
			return true;
		}
		return false;
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
}
