package com.laiba.wash.api.dto.member;

public class ListDataDto {
	

	private String detailFlag;//列表模式标记
		    
	private String pageIndex;//请求页
	    
	private String memberKey;//手机号 或者 卡号
	 
	private String pageSize;//每页显示数量
	 
	private String levelId;//级别
	 
	private Integer levelIdInt;//
	
	private String freezeStatus;//账户id
	
	private String sortFiled; // 1.memberId 2 registerDay 3.mobile 4.cardNo
	
	private String sortType;//1 ASC 2 DESC
	
	private String memberId;
	 
	public Integer getLevelIdInt() {
		return levelIdInt;
	}

	public void setLevelIdInt(Integer levelIdInt) {
		this.levelIdInt = levelIdInt;
	}

	public String getDetailFlag() {
		return detailFlag;
	}

	public void setDetailFlag(String detailFlag) {
		this.detailFlag = detailFlag;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getMemberKey() {
		return memberKey;
	}

	public void setMemberKey(String memberKey) {
		this.memberKey = memberKey;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	public String getSortFiled() {
		return sortFiled;
	}

	public void setSortFiled(String sortFiled) {
		this.sortFiled = sortFiled;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
