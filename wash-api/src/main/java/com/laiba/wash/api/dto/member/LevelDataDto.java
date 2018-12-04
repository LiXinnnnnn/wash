package com.laiba.wash.api.dto.member;

public class LevelDataDto {

	private String name;
	private String initPoints;
	private String pointsRate;
	private String initBalance;
	private String saleRate;
	private String maxPointsRate;
	private String defaultFlag;
	private String comments;	
	private String operator;
	private String levelId;
	
	private Integer initPointsInt;
	private Integer pointsRateInt;
	private Integer initBalanceInt;
	private Integer saleRateInt;
	private Integer maxPointsRateInt;
	private Integer levelIdInt;
	
	private String termId;
	
	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getLevelId() {
		return levelId;
	}
	
	public Integer getLevelIdInt() {
		return levelIdInt;
	}
	public void setLevelIdInt(Integer levelIdInt) {
		this.levelIdInt = levelIdInt;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInitPoints() {
		return initPoints;
	}
	public void setInitPoints(String initPoints) {
		this.initPoints = initPoints;
	}
	public String getPointsRate() {
		return pointsRate;
	}
	public void setPointsRate(String pointsRate) {
		this.pointsRate = pointsRate;
	}
	public String getInitBalance() {
		return initBalance;
	}
	public void setInitBalance(String initBalance) {
		this.initBalance = initBalance;
	}
	public String getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(String saleRate) {
		this.saleRate = saleRate;
	}
	public String getMaxPointsRate() {
		return maxPointsRate;
	}
	public void setMaxPointsRate(String maxPointsRate) {
		this.maxPointsRate = maxPointsRate;
	}
	public String getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	public Integer getInitPointsInt() {
		return initPointsInt;
	}
	public void setInitPointsInt(Integer initPointsInt) {
		this.initPointsInt = initPointsInt;
	}
	public Integer getPointsRateInt() {
		return pointsRateInt;
	}
	public void setPointsRateInt(Integer pointsRateInt) {
		this.pointsRateInt = pointsRateInt;
	}
	public Integer getInitBalanceInt() {
		return initBalanceInt;
	}
	public void setInitBalanceInt(Integer initBalanceInt) {
		this.initBalanceInt = initBalanceInt;
	}
	public Integer getSaleRateInt() {
		return saleRateInt;
	}
	public void setSaleRateInt(Integer saleRateInt) {
		this.saleRateInt = saleRateInt;
	}
	public Integer getMaxPointsRateInt() {
		return maxPointsRateInt;
	}
	public void setMaxPointsRateInt(Integer maxPointsRateInt) {
		this.maxPointsRateInt = maxPointsRateInt;
	}
	
}
