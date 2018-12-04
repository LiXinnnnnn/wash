package com.laiba.wash.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author summer
 *
 */
public class DateUtil{
	
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String F_YYYY_MM_DD = "yyyy-MM-dd";
	
	public static final String F_YYYY_MM_DD_2 = "yyyy/MM/dd";
	
	public static final String F_YYYY_MM_DD_HH_DD = "yyyy-MM-dd HH:mm";
	
	public static final String F_YYYY_MM_DD_12HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
	
	public static final String F_YYYY_MM_DD_24HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String F_YYYY_MM_DD_T_24HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	
	public static final String F_YYYYMMDD12HHMMSS = "yyyyMMddhhmmss";
	
	public static final String F_YYYYMMDD = "yyyyMMdd";
	
	public static final String F_YYYYMMDD24HHMMSS = "yyyyMMddHHmmss";
	/**
	 * 使用参数format格式化java.util.Date日期
	 * @param date
	 * @param format
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String parseDateToStr(Date date, String format) {
		if(null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 使用参数format解析日期字符串
	 * @param date
	 * @param format
	 * @return 
	 * Date
	 * @since  1.0.0
	 */
	public static Date parseStrToDate(String date, String format) {
		try{
			if(null == date || "".equals(date)) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		}catch(Exception e){
			log.error("parseStrToDate error", e);
			return null;
		}
	}
	
}