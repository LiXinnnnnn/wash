package com.laiba.wash.api.dto.member;

public class HistoryDto {

	 private String version;

	 private String custId;
	 
	 private String openId;
	 
	 private HistoryDtoDataDto data;

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

	public HistoryDtoDataDto getData() {
		return data;
	}

	public void setData(HistoryDtoDataDto data) {
		this.data = data;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
