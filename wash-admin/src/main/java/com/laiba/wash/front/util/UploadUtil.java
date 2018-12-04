package com.laiba.wash.front.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UploadUtil {
	
	public static String getFilePath(){
		Properties properties=new Properties();
		InputStream in = Object.class.getResourceAsStream("/packagePath.properties"); 
		String path="";
		try {
			properties.load(in);
			path=properties.getProperty("packagePath");
			File file=new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public static void main(String[] args) {
		String pathString=UploadUtil.getFilePath();
		System.out.println(pathString);
	}

}
