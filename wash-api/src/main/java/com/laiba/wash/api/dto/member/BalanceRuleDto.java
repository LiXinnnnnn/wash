package com.laiba.wash.api.dto.member;

public class BalanceRuleDto {
	
	 private String version;

	 private String custId;
	 
	 private String openId;
	 
	 private BalanceRuleDataDto data;

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

	public BalanceRuleDataDto getData() {
		return data;
	}

	public void setData(BalanceRuleDataDto data) {
		this.data = data;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
}
