package com.laiba.wash.api.dto.member;

import java.util.Date;

public class RevokeDataDto {
	
	private String clientTradeNo;
	
	private String TransTime;
	
	private Date TransTimeT;
	
	private String comments;
	
	private String operator;
	
	private String termId;

	
	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getClientTradeNo() {
		return clientTradeNo;
	}

	public void setClientTradeNo(String clientTradeNo) {
		this.clientTradeNo = clientTradeNo;
	}

	public String getTransTime() {
		return TransTime;
	}

	public void setTransTime(String transTime) {
		TransTime = transTime;
	}

	public Date getTransTimeT() {
		return TransTimeT;
	}

	public void setTransTimeT(Date transTimeT) {
		TransTimeT = transTimeT;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
