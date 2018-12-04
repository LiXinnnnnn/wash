package com.laiba.wash.mobile.util;

import java.util.Calendar;
import java.util.Date;

public class WeekUtil {

	public static String getWeekName(Date date){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		if (i == 2) {
			return "周一";
		}else if(i == 3){
			return "周二";
		}else if(i == 4){
			return "周三";
		}else if(i == 5){
			return "周四";
		}else if(i == 6){
			return "周五";
		}else if(i == 7){
			return "周六";
		}else if(i == 1){
			return "周日";
		}
		return null;
	}
	
}
