package com.laiba.wash.front.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckParameter {
	public static boolean check(Object object) {
		if (object instanceof String) {
			if (object != null && !object.equals("")) {
				return true;
			} else {
				return false;
			}
		}
		if (object instanceof Integer) {
			if (object != null && (Integer) object >= 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	 /**
     * 校验是否含有XSS攻击代码,返回非法参数
     **/
    public static String isExistXss(String[] strs) {
    	String rtnRs = null;
    	Pattern p = null;
    	Matcher m = null;
    	String[] pArr = {"<.+?>", "%3c.+?%3e", "&lt;.+?&gt;","on(.+?)=(.+?)", "on(.+?)%3(.+?)"
    			         , "onfocus=(.+?)", "onmousemove=(.+?)", "onmousemove%3(.+?)"
    			         , "javascript:(.+?)", "eval\\((.+?)"
    			         , "href=(.+?)", "href%3(.+?)"
    			         , "onclick=(.+?)"};
    	for(int k=0;k<strs.length;k++){
    		String strLowCase = strs[k].toLowerCase();
        	for (int i=0; i<pArr.length; i++) {
            	p = Pattern.compile(pArr[i]);
            	m = p.matcher(strLowCase);
            	if (m.find()){
            		rtnRs = strLowCase;
            	    break;
            	}
        	}
        	   		
    	}
    	return rtnRs;
		    	
    }

	 
}
