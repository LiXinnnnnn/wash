package com.laiba.wash.api.dto.member;

public class ResponseHistoryDataDealDto {
	
	private String clientTradeNo;
	private String memberId;
	private String balanceBefore;
	private String balance;
	private String balanceAfter;
	private String transTime;
	private String comments;
	private String operator;
	
	private String originalFee;
	private String pointsBefore;
	private String points;
	private String pointsAfter;
	private String dealType;
	private String termId;
	private String giveType;
	private String orderType;
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getGiveType() {
		return giveType;
	}
	public void setGiveType(String giveType) {
		this.giveType = giveType;
	}
	public String getClientTradeNo() {
		return clientTradeNo;
	}
	public void setClientTradeNo(String clientTradeNo) {
		this.clientTradeNo = clientTradeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBalanceBefore() {
		return balanceBefore;
	}
	public void setBalanceBefore(String balanceBefore) {
		this.balanceBefore = balanceBefore;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getBalanceAfter() {
		return balanceAfter;
	}
	public void setBalanceAfter(String balanceAfter) {
		this.balanceAfter = balanceAfter;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOriginalFee() {
		return originalFee;
	}
	public void setOriginalFee(String originalFee) {
		this.originalFee = originalFee;
	}
	public String getPointsBefore() {
		return pointsBefore;
	}
	public void setPointsBefore(String pointsBefore) {
		this.pointsBefore = pointsBefore;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getPointsAfter() {
		return pointsAfter;
	}
	public void setPointsAfter(String pointsAfter) {
		this.pointsAfter = pointsAfter;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}

}
