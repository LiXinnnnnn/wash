package com.laiba.wash.mobile.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
 /**
 * @author 	  patrick
 * @takeOver 	
 * @created 	2014年1月20日
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String view;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		HandlerMethod hm = (HandlerMethod)handler;
		log.error(hm.getBean().getClass().getName(), ex);
		ModelAndView v = new ModelAndView(view);
		v.addObject("errorMsg", ex.getMessage());
		return v;

	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

}

