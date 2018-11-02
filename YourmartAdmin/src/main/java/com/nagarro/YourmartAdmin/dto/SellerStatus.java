package com.nagarro.YourmartAdmin.dto;

public class SellerStatus {
	
	private Integer sellerId;
	
	private String status;
	
	public SellerStatus(Integer sellerId, String status) {
		this.sellerId = sellerId;
		this.status = status;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
