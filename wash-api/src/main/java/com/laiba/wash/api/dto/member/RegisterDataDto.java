package com.laiba.wash.api.dto.member;

import java.util.Date;

public class RegisterDataDto {
    
    private String cardNo;

    private String mobile;

    private String levelId;
    
    private Integer levelIdInt;

    private String memberName;

    private String memberSex;

    private String memberBirthday;

    private String freezeStatus;

    private String freezeRemark;

    private String comments;

    private Date createTime;

    private Date updateTime;

    private String status;

    private String operator;
    
    private String termId;
    

    private String memberId;//账户id
    
    private String memberKey;//手机号 或者 卡号
    
    private String membersCount;//总数量
    
    private String lastOperator;//操作员

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

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public Integer getLevelIdInt() {
		return levelIdInt;
	}

	public void setLevelIdInt(Integer levelIdInt) {
		this.levelIdInt = levelIdInt;
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

	public String getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	public String getFreezeRemark() {
		return freezeRemark;
	}

	public void setFreezeRemark(String freezeRemark) {
		this.freezeRemark = freezeRemark;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberKey() {
		return memberKey;
	}

	public void setMemberKey(String memberKey) {
		this.memberKey = memberKey;
	}

	public String getMembersCount() {
		return membersCount;
	}

	public void setMembersCount(String membersCount) {
		this.membersCount = membersCount;
	}

	public String getLastOperator() {
		return lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

}