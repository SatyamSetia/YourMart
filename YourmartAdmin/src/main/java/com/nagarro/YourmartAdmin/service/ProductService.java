package com.nagarro.YourmartAdmin.service;

import java.util.HashMap;

import com.nagarro.YourmartAdmin.dto.ProductListResponse;
import com.nagarro.YourmartAdmin.dto.ProductResponse;
import com.nagarro.YourmartAdmin.dto.ProductStatus;

public interface ProductService {

	ProductListResponse getAllProducts(HashMap<String, String> queryParams);

	ProductResponse getProductById(int parseInt);

	void updateProductStatus(ProductStatus productStatus);

}
