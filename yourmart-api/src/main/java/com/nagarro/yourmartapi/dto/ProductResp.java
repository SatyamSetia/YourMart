package com.nagarro.yourmartapi.dto;

import java.time.LocalDateTime;

import com.nagarro.yourmartapi.models.Seller;

public class ProductResp {
	
	private Integer productId;
	
	private String name;
	
	private String shortDesc;
	
	private String longDesc;
	
	private String dimensions;
	
	private String sellerProdCode;
	
	private Integer mrp;
	
	private Integer ssp;
	
	private Integer ymp;
	
	private String primaryImage;
	
	private String prodAttr;
	
	private Integer sellerId;
	
	private String comments;
	
	private String status;
	
	private String usageInstructions;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getSellerProdCode() {
		return sellerProdCode;
	}

	public void setSellerProdCode(String sellerProdCode) {
		this.sellerProdCode = sellerProdCode;
	}

	public Integer getMrp() {
		return mrp;
	}

	public void setMrp(Integer mrp) {
		this.mrp = mrp;
	}

	public Integer getSsp() {
		return ssp;
	}

	public void setSsp(Integer ssp) {
		this.ssp = ssp;
	}

	public Integer getYmp() {
		return ymp;
	}

	public void setYmp(Integer ymp) {
		this.ymp = ymp;
	}

	public String getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}

	public String getProdAttr() {
		return prodAttr;
	}

	public void setProdAttr(String prodAttr) {
		this.prodAttr = prodAttr;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsageInstructions() {
		return usageInstructions;
	}

	public void setUsageInstructions(String usageInstructions) {
		this.usageInstructions = usageInstructions;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
