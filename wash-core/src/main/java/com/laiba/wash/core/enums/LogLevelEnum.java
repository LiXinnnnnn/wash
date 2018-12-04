package com.laiba.wash.core.enums;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author Andrew
 *
 */

public enum LogLevelEnum {
    DEBUG("1"),
	INFO("2"),//通知
	WARNING("3"),//警告
	ERROR("4");//错误
	 
     // 成员变量
     private String level;

	// 构造方法
     private LogLevelEnum(String level) {
         this.level = level;

     }
     // 判断某个值是否属于枚举类的某个变量
     public static boolean checkLoglevelName(String loglevelName) {
    	
     	boolean flag=false;
     	if(StringUtils.isBlank(loglevelName)){
   	   		return flag;
   	   	}
     	for(LogLevelEnum rate:LogLevelEnum.values()){
     		if((rate.name().toString()).equals(loglevelName.trim())){
     			flag=true;
     		}
     	}
         return flag;
     }
     public String getLevel() {
 		return level;
 	}
 	public void setLevel(String level) {
 		this.level = level;
 	}

}
