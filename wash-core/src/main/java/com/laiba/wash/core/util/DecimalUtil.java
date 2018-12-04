package com.laiba.wash.core.util;

import java.math.BigDecimal;

public class DecimalUtil {
	
	
	/**
	 * a+b
	 * @param a
	 * @param b
	 * @return
	 */
	public static String plus(String a,String b){
		
		BigDecimal x = new BigDecimal(a);
		BigDecimal y = new BigDecimal(b);
		BigDecimal z = x.add(y);
		String value = z.toString();
		return value;
		
	}
	
	/**
	 * a-b
	 * @param a
	 * @param b
	 * @return
	 */
	public static String minus(String a,String b){
		BigDecimal x = new BigDecimal(a);
		BigDecimal y = new BigDecimal(b);
		BigDecimal z = x.subtract(y);
		String value = z.toString();
		return value;
	}
		
}
