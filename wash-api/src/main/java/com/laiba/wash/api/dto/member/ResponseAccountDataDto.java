package com.laiba.wash.api.dto.member;

public class ResponseAccountDataDto {
  
	private String memberId;	
	private String cardNo;
	private String mobile;	
	private String memberName;
	private String memberSex;
	private String memberBirthday;
	private String registerDay;
	private String identityCard;
	private String address;    
	private String levelId;	    
	private String levelName;
	private String comments;
	private String lastOperator;//操作员
	private String operator;//操作员
	private String freezeRemark;
	private String freezeStatus;
    //详情
	private String points;
	private String balance;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public String getMemberBirthday() {
		return memberBirthday;
	}
	public void setMemberBirthday(String memberBirthday) {
		this.memberBirthday = memberBirthday;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getFreezeRemark() {
		return freezeRemark;
	}
	public void setFreezeRemark(String freezeRemark) {
		this.freezeRemark = freezeRemark;
	}
	public String getFreezeStatus() {
		return freezeStatus;
	}
	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}
	public String getRegisterDay() {
		return registerDay;
	}
	public void setRegisterDay(String registerDay) {
		this.registerDay = registerDay;
	}
	
}