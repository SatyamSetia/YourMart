package com.nagarro.YourmartAdmin.dto;


public class SellerResponse {
	
	private int status;
	
	private SellerRespPayload payload;
	
	private String error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public SellerRespPayload getPayload() {
		return payload;
	}

	public void setPayload(SellerRespPayload payload) {
		this.payload = payload;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
