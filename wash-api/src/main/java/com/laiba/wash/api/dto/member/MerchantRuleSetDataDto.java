package com.laiba.wash.api.dto.member;

public class MerchantRuleSetDataDto {
	
	private String initPoints;
	
	private String initBalance;
		
	private String pointsRate;
	
	private String pointsUseRate;
	
	private String maxPointsRate;
	
	private String recGivePointsFlag;
	
	private String comments;
	
	private String operator;
	
	private String saleRate;

	private Integer initPointsInt;
	
	private Integer initBalanceInt;
	
	private Integer pointsRateInt;
	
	private Integer maxPointsRateInt;
	
	private Integer pointsUseRateInt;
	
	private String termId;
	
	private String openId;
	
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getPointsUseRateInt() {
		return pointsUseRateInt;
	}

	public void setPointsUseRateInt(Integer pointsUseRateInt) {
		this.pointsUseRateInt = pointsUseRateInt;
	}

	public Integer getMaxPointsRateInt() {
		return maxPointsRateInt;
	}

	public void setMaxPointsRateInt(Integer maxPointsRateInt) {
		this.maxPointsRateInt = maxPointsRateInt;
	}

	public String getInitPoints() {
		return initPoints;
	}

	public void setInitPoints(String initPoints) {
		this.initPoints = initPoints;
	}

	public String getInitBalance() {
		return initBalance;
	}

	public void setInitBalance(String initBalance) {
		this.initBalance = initBalance;
	}

	public String getMaxPointsRate() {
		return maxPointsRate;
	}

	public void setMaxPointsRate(String maxPointsRate) {
		this.maxPointsRate = maxPointsRate;
	}

	public Integer getInitPointsInt() {
		return initPointsInt;
	}

	public void setInitPointsInt(Integer initPointsInt) {
		this.initPointsInt = initPointsInt;
	}

	public Integer getInitBalanceInt() {
		return initBalanceInt;
	}

	public void setInitBalanceInt(Integer initBalanceInt) {
		this.initBalanceInt = initBalanceInt;
	}

	public Integer getPointsRateInt() {
		return pointsRateInt;
	}

	public void setPointsRateInt(Integer pointsRateInt) {
		this.pointsRateInt = pointsRateInt;
	}

	public String getPointsRate() {
		return pointsRate;
	}

	public void setPointsRate(String pointsRate) {
		this.pointsRate = pointsRate;
	}

	public String getPointsUseRate() {
		return pointsUseRate;
	}

	public void setPointsUseRate(String pointsUseRate) {
		this.pointsUseRate = pointsUseRate;
	}

	public String getRecGivePointsFlag() {
		return recGivePointsFlag;
	}

	public void setRecGivePointsFlag(String recGivePointsFlag) {
		this.recGivePointsFlag = recGivePointsFlag;
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

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}
	
	public String getSaleRate() {
		return saleRate;
	}
	
	public void setSaleRate(String saleRate) {
		this.saleRate = saleRate;
	}
}
