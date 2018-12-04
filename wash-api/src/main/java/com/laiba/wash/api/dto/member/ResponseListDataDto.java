package com.laiba.wash.api.dto.member;

import java.util.List;

public class ResponseListDataDto {
	
	private String membersCount;
	
	private String pageIndex;
	
	private String pageSize;
	
	private String totalPage;
	
	private List<ResponseAccountDataDto> members;

	
	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getMembersCount() {
		return membersCount;
	}

	public void setMembersCount(String membersCount) {
		this.membersCount = membersCount;
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

	public List<ResponseAccountDataDto> getMembers() {
		return members;
	}

	public void setMembers(List<ResponseAccountDataDto> members) {
		this.members = members;
	}
	
	
}
