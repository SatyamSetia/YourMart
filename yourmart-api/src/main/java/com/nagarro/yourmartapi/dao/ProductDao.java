package com.nagarro.yourmartapi.dao;

import java.util.List;

import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.dto.ProductResp;
import com.nagarro.yourmartapi.dto.Response;

public interface ProductDao {

	void createNewProduct(ProductDetails productDetails);

	Response<List<ProductResp>> getAllProducts(String sortBy, List<String> status, String searchKeyword,
			String searchType);

	Response<ProductResp> getProductById(Integer productId);

	Response<List<ProductResp>> getAllProductsOfSeller(Integer sellerId, String sortBy, List<String> status);

}
