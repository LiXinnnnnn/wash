package com.laiba.wash.mobile.util;

import java.util.Map;

public class HttpResult {
	private int code;
	private String body;
	private Map<String,String> headerMap;
	
	public String makeMsg(){
		return "CODE:"+code+",BODY:"+body;
	}
	
	public void changeEncode(String from,String to){
		if(body!=null){
			try {
				body = new String(body.getBytes(from),to);
			} catch (Exception e) { }
		}
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}

	/**
	 * @return
	 */
	public boolean isSuccess() {
		return code==200;
	}
	
	public boolean isTimeout() {
		return code==499||code==502||code==498;
	}
}
