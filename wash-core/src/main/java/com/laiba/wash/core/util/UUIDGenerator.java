package com.laiba.wash.core.util;
import java.util.UUID;

public class UUIDGenerator {

	 public UUIDGenerator() { 
	    } 
	    /** 
	     * 获得UUID 
	     * @return String UUID 
	     */ 
	    public static String getUUID(){ 
	        String s = UUID.randomUUID().toString(); 
	        //去掉"-"符
	        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	    } 
	    /** 
	     * 获得指定数目的UUID 
	     * @param number int 要获得的UUID数量 
	     * @return String[] UUID数组 
	     */ 
	    public static String[] getUUID(int number){ 
	        if(number < 1){ 
	            return null; 
	        } 
	        String[] ss = new String[number]; 
	        for(int i=0;i<number;i++){ 
	            ss[i] = getUUID(); 
	        } 
	        return ss; 
	    } 
	    
	    /**
	     * 获取16位uuid,重复性注意
	     * @return
	     */
	    public static String getUUID16(){
	    	return getUUID().substring(8, 24);
	    }
	    
	    public static void main(String[] args) {
	    	String strip = "https://alipass.alipay.com//temps/free/strip.png";
			System.out.println("\"strip\":\""+strip+"\",");
		}
}
