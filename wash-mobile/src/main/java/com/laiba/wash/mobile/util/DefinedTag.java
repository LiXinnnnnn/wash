/**
 * @author Summer
 * 自定义jsp标签
 * 
 */


package com.laiba.wash.mobile.util;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.laiba.wash.core.util.WashPropertyUtil;

public class DefinedTag  extends ApplicationObjectSupport{
	
	private static Logger log = LoggerFactory.getLogger(DefinedTag.class);
	private static String versionCode = null;//初始版本
	private static String basePath = null;//初始版本
					
    /**
     * 获取js基础路径
     * @param jsFilename
     * @return 
     */
    public static String getJsContext(String jsFilename) {
    	if(basePath == null ){
    		 basePath = getBasePath();
    	}
    	if(versionCode == null ){
    		versionCode = getVersionCode();
    	}
    	String jsContext = basePath+"/static/js/"+jsFilename +"?v="+versionCode;	    	
        return  jsContext;
    }
	    
    /**
     * 获取css基础路径
     * @param jsFilename
     * @return 
     */
    public static String getCssContext(String jsFilename) {	    	
    	if(basePath == null ){
    		 basePath = getBasePath();
    	}
    	if(versionCode == null ){
    		versionCode = getVersionCode();
    	}
    	String jsContext = basePath+"/static/css/"+jsFilename+"?v="+versionCode;	    	
        return  jsContext;
    }
    
    /**
     * 获取static基础路径
     * @param staticFilename
     * @return 
     */
    public static String getStaticContext(String staticFilename) {	    	
    	if(basePath == null ){
    		 basePath = getBasePath();
    	}
    	if(versionCode == null ){
    		versionCode = getVersionCode();
    	}
    	String jsContext = basePath+"/static/"+staticFilename+"?v="+versionCode;	    	
        return  jsContext;
    }
	    	    
    /**
     *获取项目基础路径 
     * @return
     */
   public static String getBasePath(){
	   try{
			if(basePath != null){
				return basePath;
			}
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
			ServletContext servletContext = webApplicationContext.getServletContext();
			basePath =servletContext.getContextPath();
			if(StringUtils.isBlank(basePath)){
				log.error("DasDefinedTag - result of getBasePath() is null");
			}
	   }catch(Exception e){
			log.error("DasDefinedTag-静态文件版本号:dasVersionCode获取异常：", e);	    		 
	   }
       return basePath; 		   
   }
	   
   /**
    * 从上下文读取配置文件，获取版本号
    * @return
    */
   public static String getVersionCode(){
		    
		try{  
			if(versionCode != null){
		    	return versionCode;
		    }
			WebApplicationContext webContext = ContextLoader .getCurrentWebApplicationContext();
			WashPropertyUtil dasPropertyUtil = (WashPropertyUtil)webContext.getBean("washPropertyUtil");
			if(dasPropertyUtil !=null && dasPropertyUtil.getProperty("wash.versioncode") != null){
				versionCode = dasPropertyUtil.getProperty("wash.versioncode");
			}else{	    			
				log.error("DasDefinedTag-静态文件版本号:dasVersionCode获取失败");
			}
		}catch(Exception e){
			 log.error("DasDefinedTag-静态文件版本号:dasVersionCode获取异常：", e);	    		 
		}
		return versionCode;
   }
	  
}
