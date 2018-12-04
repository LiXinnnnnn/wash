package com.laiba.wash.mobile.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: xzhu
 * Date: 2014/05/15
 * Time: 上午11:05
 */
public class WebUtil {

    /**
     * 字符串编码
     *
     * @param sStr
     * @param sEnc
     * @return String
     */
    public final static String UrlEncoder(String sStr, String sEnc) {
        String sReturnCode = "";
        try {
            sReturnCode = URLEncoder.encode(sStr, sEnc);
        } catch (UnsupportedEncodingException ex) {

        }
        return sReturnCode;
    }

    public final static String UrlEncoder(String sStr) {
        return UrlEncoder(sStr,"UTF-8");
    }
    public final static String UrlDecoder(String sStr) {
        return UrlDecoder(sStr,"UTF-8");
    }
    
    
    /**
     * 字符串解码
     *
     * @param sStr
     * @param sEnc
     * @return String
     */
    public final static String UrlDecoder(String sStr, String sEnc) {
        String sReturnCode = "";
        try {
            sReturnCode = URLDecoder.decode(sStr, sEnc);
        } catch (UnsupportedEncodingException ex) {

        }
        return sReturnCode;
    }

    public final static String getIpAddr(HttpServletRequest request) {
    	String ip = request.getHeader("HTTP_XIP");
    	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
    	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip=request.getRemoteAddr();
		}
        
        if(ip != null && ip.length() > 20){//兼容坑爹的问题,丁总那边居然会返回两个ip
        	ip = ip.substring(0,20);
        }
        return ip;
    }

    //打印header
    @SuppressWarnings("rawtypes")
    public static void printHeader(HttpServletRequest request) {
        Enumeration names = request.getHeaderNames();
        StringBuilder sb = new StringBuilder("headerInfo---");
        while(names.hasMoreElements()) {
            String name = names.nextElement().toString();
            Enumeration headers = request.getHeaders(name);
            sb.append(name).append(":");
            while(headers.hasMoreElements()) {
                sb.append(headers.nextElement()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static String getBasePath(HttpServletRequest request){
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    }
}
