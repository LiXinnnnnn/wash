package com.laiba.wash.api.dto.member;

public class RequestHeaderDto {
	 
    private String version ;//版本
    
    private String custId ;//商户号
    
    private String openId ;//
    
    private String appId ;//接入商编号

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}