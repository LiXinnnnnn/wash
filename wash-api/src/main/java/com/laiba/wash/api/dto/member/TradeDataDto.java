package com.laiba.wash.api.dto.member;

import java.util.Date;

public class TradeDataDto {
	
	private String memberId;
	
	private String clientTradeNo;
	
	private String TransTime;
	
	private Date TransTimeT;
	
	private String originalFee;
	
	private String balanceRuleId;
	
	private String comments;
	
	private String operator;
	
	
	private Integer originalFeeInt;
	
	private String points;
	
	private Integer pointsInt;
	
	private String accountValue;
	
	private Integer accountValueInt;
	
	private Integer balanceRuleIdInt;

	private String termId;
	
	private String giveType;
	
	private String activityFee;
	
	private Integer activityFeeInt;
	
	public String getActivityFee() {
		return activityFee;
	}

	public void setActivityFee(String activityFee) {
		this.activityFee = activityFee;
	}

	public Integer getActivityFeeInt() {
		return activityFeeInt;
	}

	public void setActivityFeeInt(Integer activityFeeInt) {
		this.activityFeeInt = activityFeeInt;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getBalanceRuleId() {
		return balanceRuleId;
	}

	public void setBalanceRuleId(String balanceRuleId) {
		this.balanceRuleId = balanceRuleId;
	}

	public Integer getBalanceRuleIdInt() {
		return balanceRuleIdInt;
	}

	public void setBalanceRuleIdInt(Integer balanceRuleIdInt) {
		this.balanceRuleIdInt = balanceRuleIdInt;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getClientTradeNo() {
		return clientTradeNo;
	}

	public void setClientTradeNo(String clientTradeNo) {
		this.clientTradeNo = clientTradeNo;
	}

	public String getOriginalFee() {
		return originalFee;
	}

	public void setOriginalFee(String originalFee) {
		this.originalFee = originalFee;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getAccountValue() {
		return accountValue;
	}

	public void setAccountValue(String accountValue) {
		this.accountValue = accountValue;
	}

	public Integer getOriginalFeeInt() {
		return originalFeeInt;
	}

	public void setOriginalFeeInt(Integer originalFeeInt) {
		this.originalFeeInt = originalFeeInt;
	}

	public Integer getPointsInt() {
		return pointsInt;
	}

	public void setPointsInt(Integer pointsInt) {
		this.pointsInt = pointsInt;
	}

	public Integer getAccountValueInt() {
		return accountValueInt;
	}

	public void setAccountValueInt(Integer accountValueInt) {
		this.accountValueInt = accountValueInt;
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

	public String getGiveType() {
		return giveType;
	}

	public void setGiveType(String giveType) {
		this.giveType = giveType;
	}
	
}
