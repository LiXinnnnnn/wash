package com.laiba.wash.api.dto.member;

import java.util.Date;

public class HistoryDtoDataDto {
	
	private String startTime;
	
	private String endTime;
	
	private Date startTimeDate;
	
	private Date endTimeDate;
	
	private String pageIndex;//请求页
	    
	private String pageSize;//每页显示数量
	    
	private String clientTradeNo;//交易号
	
	private String memberId;//会员id
	
	private Integer memberIdInt;//会员id

	private String termId;
	
	private String giveType;
	
	private String queryType;
	
	private String sortFiled; //
	
	private String sortType;//1 ASC 2 DESC

	public Integer getMemberIdInt() {
		return memberIdInt;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setMemberIdInt(Integer memberIdInt) {
		this.memberIdInt = memberIdInt;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getEndTimeDate() {
		return endTimeDate;
	}

	public void setEndTimeDate(Date endTimeDate) {
		this.endTimeDate = endTimeDate;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Date getStartTimeDate() {
		return startTimeDate;
	}

	public void setStartTimeDate(Date startTimeDate) {
		this.startTimeDate = startTimeDate;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getGiveType() {
		return giveType;
	}

	public void setGiveType(String giveType) {
		this.giveType = giveType;
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
	
}
