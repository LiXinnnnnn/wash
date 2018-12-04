package com.laiba.wash.mobile.util;

public class MultipartData {
	private byte[] data;
	private String paramName;
	private String fileName;
	
	
	public MultipartData(byte[] data,String paramName,String fileName){
		this.data = data;
		this.paramName = paramName;
		this.fileName = fileName;
		
	}
	
	public MultipartData(){
		
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	
	
}
