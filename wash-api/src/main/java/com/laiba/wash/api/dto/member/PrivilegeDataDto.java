package com.laiba.wash.api.dto.member;

public class PrivilegeDataDto {
	
	private String memberId;
	
	private String originalFee;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOriginalFee() {
		return originalFee;
	}

	public void setOriginalFee(String originalFee) {
		this.originalFee = originalFee;
	}

}
