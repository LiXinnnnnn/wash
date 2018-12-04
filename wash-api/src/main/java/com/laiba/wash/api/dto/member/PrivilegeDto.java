package com.laiba.wash.api.dto.member;

public class PrivilegeDto {

	 private String custId;
	 
	 private String openId;

	 private PrivilegeDataDto data;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public PrivilegeDataDto getData() {
		return data;
	}

	public void setData(PrivilegeDataDto data) {
		this.data = data;
	}

}
