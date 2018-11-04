package com.nagarro.yourmartapi.services;

import java.util.List;

import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.dto.ProductResp;
import com.nagarro.yourmartapi.dto.ProductStatus;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Product;

public interface ProductService {

	void addProduct(ProductDetails productDetails);

	Response<List<ProductResp>> fetchAllProducts(String sortBy, List<String> status, String searchKeyword,
			String searchType);

	Response<ProductResp> fetchSingleProduct(Integer productId);

	Response<List<ProductResp>> fetchProductsOfSeller(Integer sellerId, String sortBy, List<String> status);

	void updateProduct(Integer productId, ProductDetails productDetails);

	Response<Product> updateProductStatus(Integer productId, ProductStatus productStatus);

}