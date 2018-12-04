package com.laiba.wash.api.dto.member;

import java.util.List;

public class ResponseHistoryDataDto {
	
	private String dealsCount;
	
	private String pageIndex;
	
	private String pageSize;
	
	private String totalPage;
	
	private List<ResponseHistoryDataDealDto> deals;

	public String getDealsCount() {
		return dealsCount;
	}

	public void setDealsCount(String dealsCount) {
		this.dealsCount = dealsCount;
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

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public List<ResponseHistoryDataDealDto> getDeals() {
		return deals;
	}

	public void setDeals(List<ResponseHistoryDataDealDto> deals) {
		this.deals = deals;
	}
}
