package com.nagarro.YourmartAdmin.dto;

import java.util.List;

public class ProductListResponse {
	
	private int status;
	
	private List<ProductRespPayload> payload;
	
	private String error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<ProductRespPayload> getPayload() {
		return payload;
	}

	public void setPayload(List<ProductRespPayload> payload) {
		this.payload = payload;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
