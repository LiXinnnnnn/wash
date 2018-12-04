/**
 * ProjectName:  TODO
 * WotPropertyPlaceholderConfigurer.java
 * cn.wsn.wot.core.util
 *
 * Function： TODO 
 *
 *   ver     date      		author       email
 * ─────────────────────────────────────────────
 *   		 2014年1月3日 		patrick        cuiping@wsn.cn
 *
 * Copyright (c) 2014, WSN All Rights Reserved.
*/
package com.laiba.wash.core.util;

import java.util.Properties;

import org.springframework.beans.BeansException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
 /**
 * @author 	  patrick
 * @takeOver 	
 * @created 	2014年1月3日
 */
public class WashPropertyUtil extends
		PropertyPlaceholderConfigurer {
	private Properties props;
	
	@Override  
    protected void processProperties(  
            ConfigurableListableBeanFactory beanFactoryToProcess,  
            Properties props) throws BeansException {  
        super.processProperties(beanFactoryToProcess, props);  
        this.props=props;  
    }
	
	public String getProperty(String key) {  
        return props.getProperty(key);  
    }
}

