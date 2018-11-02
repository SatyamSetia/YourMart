package com.nagarro.yourmartapi.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)
	private Integer productId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "short_desc", nullable = false)
	private String shortDesc;
	
	@Column(name = "long_desc")
	private String longDesc;
	
	@Column(name = "dimensions")
	private String dimensions;
	
	@Column(name = "seller_prod_code", nullable = false)
	private String sellerProdCode;
	
	@Column(name = "mrp", nullable = false)
	private Integer mrp;
	
	@Column(name = "ssp")
	private Integer ssp;
	
	@Column(name = "ymp", nullable = false)
	private Integer ymp;
	
	@Column(name = "primary_image", nullable = false)
	private String primaryImage;
	
	@Column(name = "prod_attributes")
	private String prodAttr;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="seller_id")
	private Seller seller;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "usage_instructions")
	private String usageInstructions;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
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

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
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
