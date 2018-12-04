package com.laiba.wash.api.util;  

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:DESUtil
 *
 * @author   summer
 
 * @Date	 2016-1-25
 */
public class DESUtil {
	
	private static Logger log = LoggerFactory.getLogger(DESUtil.class);
	
	/**
	 * 加密
	 * @method encrypt
	 * @param content	需要加密的内容
	 * @param password	加密密码
	 * @return
	 * @throws 
	 * @since v1.0
	 */
	/*public static byte[] encrypt(String content, String password){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (Exception e) {
			log.error("DES encrypt exception:", e);
		}
		return null;
	}*/
	
	/**
	 * 解密
	 * @method decrypt
	 * @param content	待解密内容
	 * @param password	解密密钥
	 * @return
	 * @throws 
	 * @since v1.0
	 */
	/*public static byte[] decrypt(byte[] content, String password){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 解密
		} catch (Exception e) {
			log.error("DES decrypt exception:", e);
		}
		return null;
	}*/
	
	/**
	 * 将二进制转换成16进制
	 * @method parseByte2HexStr
	 * @param buf
	 * @return
	 * @throws 
	 * @since v1.0
	 */
	public static String parseByte2HexStr(byte buf[]){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < buf.length; i++){
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	/**
	 * 将16进制转换为二进制
	 * @method parseHexStr2Byte
	 * @param hexStr
	 * @return
	 * @throws 
	 * @since v1.0
	 */
	public static byte[] parseHexStr2Byte(String hexStr){
		if(hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	
	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static byte[] DES_ENCRYPT(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			log.error("DES DES_ENCRYPT exception:", e);
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] DES_DECRYPT(byte[] src, String password) throws Exception {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return cipher.doFinal(src);
		} catch (Throwable e) {
			log.error("DES DES_DECRYPT exception:", e);
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		/*String body = " cpuid:00239E03185F,";
		String password = "12345678";
		//测试用，模拟机器加密	    	
    	/*byte[] encryptResult = DESUtil.encrypt(body, password);//加密成byte[]	    						
		String encryptResultStr = DESUtil.parseByte2HexStr(encryptResult);//byte[] 转字符串
		   System.out.println("加密后：" + encryptResultStr);*/
		
		/*byte[] decryptFrom = DESUtil.parseHexStr2Byte(encryptResultStr);			
		byte[] decryptResult = DESUtil.decrypt(decryptFrom,password);//解码		   
		   System.out.println("解密后：" + new String(decryptResult));*/
		
		
		/*String key =MD5Util.encryptMD5("00239E03");
	    String datasource = "{\"cpuid\":\"00239E03185F\",\"deviceInfo\":{\"ip\":\"127.0.0.1\"}}";
	    byte[] eValue = DES_ENCRYPT(datasource.getBytes(), key);
	    String encryptResultStr = DESUtil.parseByte2HexStr(eValue);//byte[] 转字符串
	    System.out.println("加密后：" + encryptResultStr);
		
	    byte[] decryptFrom = DESUtil.parseHexStr2Byte(encryptResultStr);			
		byte[] decryptResult = DESUtil.DES_DECRYPT(decryptFrom, key);//解码		   
		   System.out.println("解密后：" + new String(decryptResult));*/
		/*StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"code\":" + "哈哈" + ",");
		sb.append("\"msg\":\"" + "哦哦" + "\"");
		byte[] sbByte = sb.toString().getBytes("UTF8");
		byte[] encryptResult = DESUtil.DES_ENCRYPT(sbByte, "21bfbb32");//加密成byte[]	
		String body = DESUtil.parseByte2HexStr(encryptResult);
		System.out.println("加密后：" + body);
		
		byte[] decryptFrom = DESUtil.parseHexStr2Byte(body);
    	byte[] decryptResult = DESUtil.DES_DECRYPT(decryptFrom, "21bfbb32");//解码
    	System.out.println("解密后：" + new String(decryptResult));*/
	}
}

