package com.laiba.wash.api.dto.member;

public class RevokeDto {

	 private String version;

	 private String custId;
	 
	 private String openId;

	 private RevokeDataDto data;

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

	public RevokeDataDto getData() {
		return data;
	}

	public void setData(RevokeDataDto data) {
		this.data = data;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	
}
