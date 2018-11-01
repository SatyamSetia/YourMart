package com.nagarro.YourmartAdmin.dto;

import java.util.List;

public class SellerListResponse {
	
	private int status;
	
	private List<SellerRespPayload> payload;
	
	private String error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<SellerRespPayload> getPayload() {
		return payload;
	}

	public void setPayload(List<SellerRespPayload> payload) {
		this.payload = payload;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
