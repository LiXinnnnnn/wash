package com.laiba.wash.api.dto.member;

import java.util.List;

public class ResponseBalanceRulelListDto {
	
	private List<BalanceRuleDataDto> data;

	public List<BalanceRuleDataDto> getData() {
		return data;
	}

	public void setData(List<BalanceRuleDataDto> data) {
		this.data = data;
	}
	
}
