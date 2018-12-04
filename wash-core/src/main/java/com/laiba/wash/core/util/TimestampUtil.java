package com.laiba.wash.core.util;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Andrew
 *
 */

public class TimestampUtil {
	/**
	 * 字符串转化为timestamp
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp parseStringToTimestamp(String date, String format)
			throws ParseException {	
		SimpleDateFormat dFormat = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dFormat.parse(date));
		return  new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取上个月时间
	 * 
	 * @param dateNow
	 * @return
	 */
	public static Timestamp getMonthBefore(Timestamp dateNow) {
		Calendar from = Calendar.getInstance();
		from.setTime(dateNow);
		from.add(Calendar.MONTH, -1);
		Timestamp lastMonth = new Timestamp(from.getTimeInMillis());
		return lastMonth;
	}

	/**
	 * 将Timestamp转化为yyyy-MM-dd格式
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp getToday(Timestamp date, String format) {
		
		try {
			Calendar calendar = Calendar.getInstance();
			//SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			java.util.Date dateTDate = dFormat.parse(dFormat.format(date));
			calendar.setTime(dateTDate);
			return new Timestamp(calendar.getTimeInMillis());
		} catch (Exception e) {
			return null;
		}		

	}

	/**
	 * 获取当前时间前35分钟时间
	 * 
	 * @param dateNow当前时间
	 * @return 当前时间前minute分钟
	 */
	public static Timestamp getMinuteBefore(Timestamp dateNow, int minute) {
		Calendar now = Calendar.getInstance();
		now.setTime(dateNow);
		now.add(Calendar.MINUTE, -minute);
		return new Timestamp(now.getTimeInMillis());
	}


	/**
	 * 获取前n天的时间列表
	 * 
	 * @param days
	 * @return
	 */
	public static List<String> getdaysList(int days, String format) {
		List<String> timeList = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat(format);
		for (int i = days; i >= 1; i--) {
			calendar.setTime(new Date(System.currentTimeMillis()));
			calendar.add(Calendar.DATE, -i);
			timeList.add(df.format(new Date(calendar.getTimeInMillis())));
		}
		return timeList;
	}

	/**
	 * @param begin
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getDayTimeList(String begin, String end, String format)
			throws ParseException {
		List<String> timeList = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat(format);
		java.util.Date timeBegin = null;
		java.util.Date timeEnd = null;
		if (StringUtils.isBlank(begin)) {
			timeBegin = new Date();
		}else {
			timeBegin = df.parse(begin);
		}
		if (StringUtils.isBlank(end)) {
			timeEnd = new Date();
		}else {
			timeEnd = df.parse(end);
		}
		
		calendar.setTime(timeEnd);
		long time1 = calendar.getTimeInMillis();
		calendar.setTime(timeBegin);
		long time2 = calendar.getTimeInMillis();
		long between_days = (time1 - time2) / (1000 * 3600 * 24);
		timeList.add(df.format(new Date(time2)));
		for (int i = 1; i <= between_days; i++) {
			calendar.add(Calendar.DATE, +1);
			timeList.add(df.format(new Date(calendar.getTimeInMillis())));
		}
		return timeList;

	}

	/**
	 * 得出两个时间相差多少分钟
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	public static int getTimeSubtract(Timestamp dateFrom, Timestamp dateTo) {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.setTime(dateFrom);
		to.setTime(dateTo);
		int minute = (int) ((from.getTimeInMillis() - to.getTimeInMillis()) / 60000);
		return minute;

	}
	
	public static int getTimeSubtract(Date dateFrom, Date dateTo) {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.setTime(dateFrom);
		to.setTime(dateTo);
		int minute = (int) ((from.getTimeInMillis() - to.getTimeInMillis()) / 60000);
		return minute;

	}

	/**
	 * 获取一天已经过去多少分钟
	 * 
	 * @param dateFrom
	 * @return
	 */
	public static int getTimePastInOneDay(Timestamp dateFrom) {
		Calendar from = Calendar.getInstance();
		from.setTime(dateFrom);
		int hour = from.get(Calendar.HOUR_OF_DAY);
		int minute = from.get(Calendar.MINUTE);
		int minuteAll = (hour * 60 + minute);
		return minuteAll;

	}
	
	public static int getTimePastInOneDay(Date dateFrom) {
		Calendar from = Calendar.getInstance();
		from.setTime(dateFrom);
		int hour = from.get(Calendar.HOUR_OF_DAY);
		int minute = from.get(Calendar.MINUTE);
		int minuteAll = (hour * 60 + minute);
		return minuteAll;

	}

	/**
	 * 获取n天前的时间
	 * 
	 * @param dateFrom
	 * @param n
	 * @return
	 */
	public static Timestamp getDayBefore(Timestamp dateFrom, int n) {
		Calendar from = Calendar.getInstance();
		from.setTime(dateFrom);
		from.add(Calendar.DATE, -n);
		Timestamp timestamp = new Timestamp(from.getTimeInMillis());
		return timestamp;

	}
	
	/**
	 * 
	 * @param dateFrom
	 * @param n
	 * @return Timestamp
	 */
	public static Timestamp getBeforeDay( int n, String format){
		Calendar c = Calendar.getInstance();    
        try {  
            int day = c.get(Calendar.DATE);  
            c.set(Calendar.DATE, day - n);
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String dayBefore = sdf.format(c.getTime());
            Date date = sdf.parse(dayBefore);
            return (date == null ? null : new Timestamp(date.getTime()));
        } catch (Exception e) {
            return null;
        }  
	}
	
	/**
	 * 
	 * @param dateFrom
	 * @param n
	 * @return String
	 */
	public static String getBeforeDayStr( int n, String format){
		Calendar c = Calendar.getInstance();    
        try {  
            int day = c.get(Calendar.DATE);  
            c.set(Calendar.DATE, day - n);
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(c.getTime());           
        } catch (Exception e) {
            return null;
        }  
	}
	
	/**
	 * 获取两个时间段之间的月份
	 * 
	 * @param minDate, maxDate
	 * @param n
	 * @return
	 * @throws ParseException 
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate, String format) throws ParseException {
	    
		ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化为年月
	    Calendar min = Calendar.getInstance();
	    Calendar max = Calendar.getInstance();
	    min.setTime(sdf.parse(minDate));
	    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
	    max.setTime(sdf.parse(maxDate));
	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
	    Calendar curr = min;
	    while (curr.before(max)) {
	     result.add(sdf.format(curr.getTime()));
	     curr.add(Calendar.MONTH, 1);
	    }
	    return result;
	  }

}
