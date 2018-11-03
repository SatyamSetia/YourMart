package com.nagarro.YourmartAdmin.dto;

public class ProductResponse {
	
	private int status;
	
	private ProductRespPayload payload;
	
	private String error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ProductRespPayload getPayload() {
		return payload;
	}

	public void setPayload(ProductRespPayload payload) {
		this.payload = payload;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
