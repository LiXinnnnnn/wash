package com.laiba.wash.api.dto.member;

public class BalanceRuleDataDto {
	
	private String levelId;
	private String balanceRuleId;
	private String initBalance;
	private String giveBalance;
	private String comments;	
	private String operator;
	private String termId;
	
	private Integer levelIdInt;
	private Integer balanceRuleIdInt;
	private Integer initBalanceInt;
	private Integer giveBalanceInt;
	
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
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getInitBalance() {
		return initBalance;
	}
	public void setInitBalance(String initBalance) {
		this.initBalance = initBalance;
	}
	public String getGiveBalance() {
		return giveBalance;
	}
	public void setGiveBalance(String giveBalance) {
		this.giveBalance = giveBalance;
	}
	public Integer getLevelIdInt() {
		return levelIdInt;
	}
	public void setLevelIdInt(Integer levelIdInt) {
		this.levelIdInt = levelIdInt;
	}
	public Integer getInitBalanceInt() {
		return initBalanceInt;
	}
	public void setInitBalanceInt(Integer initBalanceInt) {
		this.initBalanceInt = initBalanceInt;
	}
	public Integer getGiveBalanceInt() {
		return giveBalanceInt;
	}
	public void setGiveBalanceInt(Integer giveBalanceInt) {
		this.giveBalanceInt = giveBalanceInt;
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

}
