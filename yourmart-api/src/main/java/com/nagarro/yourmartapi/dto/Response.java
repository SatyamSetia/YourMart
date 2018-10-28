package com.nagarro.yourmartapi.dto;

public class Response<T> {
	
	private int status;
	
	private T payload;
	
	private String error;
	
	public Response(int status,T payload,String error) {
		this.status = status;
		this.payload = payload;
		this.error = error;
	}

	public int getStatus() {
		return status;
	}

	public T getPayload() {
		return payload;
	}

	public String getError() {
		return error;
	}

}
