package com.nagarro.yourmartapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.ProductDao;
import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.dto.ProductResp;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.services.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	public void addProduct(ProductDetails productDetails) {
		this.productDao.createNewProduct(productDetails);		
	}

	public Response<List<ProductResp>> fetchAllProducts(List<String> sortBy, String status, String searchKeyword,
			String searchType) {
		return this.productDao.getAllProducts(sortBy, status, searchKeyword, searchType);
	}
}
