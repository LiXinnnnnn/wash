package com.laiba.wash.core.service.wechat.js;

import java.security.MessageDigest;
import java.util.Random;


public class WechatPayJs {

	private String appId;

	private String timeStamp;
	
	private String nonceStr;
	
	private String packageString;
	
	private String signType;
	
	private String paySign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getPackageString() {
		return packageString;
	}

	public void setPackageString(String packageString) {
		this.packageString = packageString;
	}
	
	public void setNonceStr(int length) {
		Random random = new Random();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(2);
			long result = 0;

			switch (number) {
			case 0:
				result = Math.round(Math.random() * 25 + 65);
				sb.append(String.valueOf((char) result));
				break;
			case 1:

				sb.append(String.valueOf(new Random().nextInt(10)));
				break;
			}
		}
		this.nonceStr = sb.toString();
	}

	public void setPaySign(String key,String signType) {
		String stringA = "appId=" + getAppId() + "&nonceStr=" + getNonceStr() + "&package=" + getPackageString()
				+ "&signType=" + getSignType() + "&timeStamp=" + getTimeStamp();
		String signTemp = stringA + "&key=" + key;
		System.out.println(signTemp);
		this.paySign = md5(signTemp);
	}

	@Override
	public String toString() {
		return "<xml><appId>" + appId + "</appId> <timeStamp>" + timeStamp
				+ "</timeStamp> <nonceStr>" + nonceStr + "</nonceStr> <packageString>" + packageString
				+ "</packageString> <signTyp>" + signType + "</signTyp> <paySign>" + paySign + "</paySign></xml>";
	}
	
	private static String md5(String string){
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");

			byte[] md = mdTemp.digest(string.getBytes("UTF-8"));
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf).toUpperCase();
		}catch (Exception e) {
			return "";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(md5("appId=wxd3f8edaaba8f9d03&nonceStr=U46N5XII096SE22OS9SME1L94E8PLA&package=prepay_id=wx2017091415153567066032ab0355256681&signTyp=MD5&timeStamp=1505373334&key=MmZMFg52bc71Melrm3Zm3fcB2jXNvgeR"));
	}
	
}
