package com.laiba.wash.api.dto.member;

public class RegisterDto{
	
    private String version;

    private String custId;
    
    private String openId;

    private RegisterDataDto data;

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

	public RegisterDataDto getData() {
		return data;
	}

	public void setData(RegisterDataDto data) {
		this.data = data;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	
}