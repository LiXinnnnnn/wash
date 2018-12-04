package com.laiba.wash.core.util;

import java.util.Random;

public class CouponCodeUtil {
	
	
	/**
	 * 距离1970-1-1 00：00：00的毫秒数,传进来的1位参数,2位随机数（10-99随机）
	 * @return
	 */
	public static String getCode(int param){
		//13位毫秒数，未来几百年都是13位
		long time = System.currentTimeMillis();
		Random random = new Random();
		int randomInt = random.nextInt(90) + 10;
		String codeString = time+ "" + param + "" + randomInt;
		return codeString;
	}

	
	public static void main(String[] args) {
	
	}
}
