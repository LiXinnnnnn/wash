/**
 * ProjectName:  TODO
 * WotExceptionResolver.java
 * cn.wsn.wot.front.interceptor
 *
 * Function： TODO 
 *
 *   ver     date      		author       email
 * ─────────────────────────────────────────────
 *   		 2014年1月20日 		patrick        cuiping@wsn.cn
 *
 * Copyright (c) 2014, WSN All Rights Reserved.
*/
package com.laiba.wash.front.interceptor;

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
public class WashExceptionResolver implements HandlerExceptionResolver {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String view;
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		String str = "empty handler bean";
		
		if(handler != null){
			HandlerMethod hm = (HandlerMethod)handler;
			Object bean = hm.getBean();
			if(bean != null){				
				str = bean.getClass().getName();
			}
		}
		log.error(str, ex);
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

