package com.laiba.wash.mobile.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {
	
	static MessageDigest MD5 = null;
	
	static Logger log = LoggerFactory.getLogger(MD5Util.class);

    static {
        try {
        	MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
        	log.error("MD5Util init error", ne);
        }
    }
	/**
	 * 获得字符串的MD5值
	 * @param strInput
	 * @return
	 * @throws Exception
	 */
    public static String encryptMD5(String strInput) throws Exception{
        StringBuffer buf = null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(strInput.getBytes("UTF-8"));
        byte b[] = md.digest();
        buf = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            if ((b[i] & 0xff) < 0x10) { /* & 0xff转换无符号整型 */
                buf.append("0");
            }

            buf.append(Long.toHexString(b[i] & 0xff)); /* 转换16进制,下方法同 */
        }
        
        return buf.toString();
    }
    
    /**
     * 获得文件的MD5值
     * @param file
     * @return
     */
    public static String getMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
        	fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
            MD5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (Exception e) {
        	log.error("MD5 file error", e);
            return null;
        } finally {
            try {
                if (fileInputStream != null)
                fileInputStream.close();
            } catch (IOException e) {
            	log.error("close file error", e);
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
    
		System.out.println(MD5Util.encryptMD5("123"));
//    	File file=new File("e:/uploadPackage/3.jpg");
//    	System.out.println(MD5Util.getMD5(file));
//    	System.out.println(file.length()/1024.0);
	}
}
