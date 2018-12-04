package com.laiba.wash.api.dto.member;

public class ResponseTradeDataDto {
  
	private String resultPoints;
	
	private String resultBalance; 
	
	private String sendPoints;
	

	public String getSendPoints() {
		return sendPoints;
	}

	public void setSendPoints(String sendPoints) {
		this.sendPoints = sendPoints;
	}

	public String getResultPoints() {
		return resultPoints;
	}

	public void setResultPoints(String resultPoints) {
		this.resultPoints = resultPoints;
	}

	public String getResultBalance() {
		return resultBalance;
	}

	public void setResultBalance(String resultBalance) {
		this.resultBalance = resultBalance;
	}

	
    
}