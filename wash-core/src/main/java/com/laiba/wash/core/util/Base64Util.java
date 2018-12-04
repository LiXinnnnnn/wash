package com.laiba.wash.core.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

public class Base64Util {
	
	private final static Base64 base64 = new Base64();
	/**
	 * 把通过base64编码的字符串转换成文件
	 * @param encode
	 * @param file
	 * @throws IOException
	 */
	public static void stringToFile(String encode,File file) throws IOException{	
		byte[] target;		
		if (encode == null) {
			return;
		}
		if (encode.length() == 0) {
			return;
		}		
		target = base64.decode(encode.getBytes("UTF-8"));		
		if (!file.exists()) {
			file.createNewFile();
		}
	    OutputStream output = new FileOutputStream(file);
	    BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
	    bufferedOutput.write(target);
	    bufferedOutput.close();		
	}
	
	public static byte[] stringToByte(String encode) throws IOException{
		byte[] target;		
		if (encode == null) {
			return null;
		}
		if (encode.length() == 0) {
			return null;
		}		
		target = base64.decode(encode.getBytes("UTF-8"));	
		return target;
	}
	
	/**
	 * 把文件通过base64编码转换为字符串
	 * @param file
	 * @return
	 */
	public static String fileToString(File file){
		byte[] source = getFileToByte(file);
		String encode = Base64Utils.encodeToString(source);
		return encode;
	}
	
	/**
	 * 文件转换为二进制
	 * @param file
	 * @return
	 */
	public static byte[] getFileToByte(File file) {
		byte[] by = new byte[(int) file.length()];
		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			byte[] bb = new byte[2048];
			int ch;
			ch = is.read(bb);
			while (ch != -1) {
				bytestream.write(bb, 0, ch);
				ch = is.read(bb);
			}
			by = bytestream.toByteArray();
			is.close();
			return by;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException {
		String string = "I05PX0xPR0lOICANCi9vcGVyYXRvci9sb2dpbi5odG09Tk9fTE9HSU4NCi9vcGVyYXRvci9hamF4L2xvZ2luU3VibWl0Lmh0bT1OT19MT0dJTg0KL29wZXJhdG9yL2xvZ2luL2thcHRjaGEuaHRtPU5PX0xPR0lODQojT1BFUkFUT1INCi9vcGVyYXRvci9sb2dvdXQuaHRtPU9QRVJBVE9SDQovb3BlcmF0b3IvdXBkYXRlUGFzc3dvcmRQYWdlLmh0bT1PUEVSQVRPUg0KL29wZXJhdG9yL2FqYXgvdXBkYXRlUGFzc3dvcmQuaHRtPU9QRVJBVE9SDQovb3BlcmF0b3IvaW5kZXguaHRtPU9QRVJBVE9SDQovb3BlcmF0b3Ivbm9BdXRob3JpdHkuaHRtPU9QRVJBVE9SDQoNCiNBRE1JTg0KL29wZXJhdG9yL29wZXJhdG9yTGlzdC5odG09QURNSU4NCi9vcGVyYXRvci9vcGVyYXRvckFkZC5odG09QURNSU4NCi9vcGVyYXRvci9hamF4L2FkZC5odG09QURNSU4NCi9vcGVyYXRvci9hamF4L2RlbGV0ZS5odG09QURNSU4NCi9vcGVyYXRvci9vcGVyYXRvclVwZGF0ZS5odG09QURNSU4NCi9vcGVyYXRvci9hamF4L3VwZGF0ZS5odG09QURNSU4NCg";
		File file = new File("F://1.properties");
		stringToFile(string, file);
		
		
	}

	
	static class CommonsCodecBase64Delegate implements Base64Delegate {

		private final org.apache.commons.codec.binary.Base64 base64 =
				new org.apache.commons.codec.binary.Base64();

		private final org.apache.commons.codec.binary.Base64 base64UrlSafe =
				new org.apache.commons.codec.binary.Base64(0, null, true);

		@Override
		public byte[] encode(byte[] src) {
			return this.base64.encode(src);
		}

		@Override
		public byte[] decode(byte[] src) {
			return this.base64.decode(src);
		}

		@Override
		public byte[] encodeUrlSafe(byte[] src) {
			return this.base64UrlSafe.encode(src);
		}

		@Override
		public byte[] decodeUrlSafe(byte[] src) {
			return this.base64UrlSafe.decode(src);
		}

	}
	
	interface Base64Delegate {

		byte[] encode(byte[] src);

		byte[] decode(byte[] src);

		byte[] encodeUrlSafe(byte[] src);

		byte[] decodeUrlSafe(byte[] src);
	}
}
