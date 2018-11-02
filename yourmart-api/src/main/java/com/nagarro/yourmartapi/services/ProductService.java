package com.nagarro.yourmartapi.services;

import java.util.List;

import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.dto.ProductResp;
import com.nagarro.yourmartapi.dto.Response;

public interface ProductService {

	void addProduct(ProductDetails productDetails);

	Response<List<ProductResp>> fetchAllProducts(List<String> sortBy, String status, String searchKeyword,
			String searchType);

}