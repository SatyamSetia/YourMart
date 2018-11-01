package com.nagarro.YourmartAdmin.dto;

public class LoginResponse {
	
	private int status;
	
	private LoginRespPayload payload;
	
	private String error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LoginRespPayload getPayload() {
		return payload;
	}

	public void setPayload(LoginRespPayload payload) {
		this.payload = payload;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
