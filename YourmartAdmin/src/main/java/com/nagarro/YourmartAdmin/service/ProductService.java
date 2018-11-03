package com.nagarro.YourmartAdmin.service;

import java.util.HashMap;

import com.nagarro.YourmartAdmin.dto.ProductListResponse;
import com.nagarro.YourmartAdmin.dto.ProductResponse;

public interface ProductService {

	ProductListResponse getAllProducts(HashMap<String, String> queryParams);

	ProductResponse getProductById(int parseInt);

}
