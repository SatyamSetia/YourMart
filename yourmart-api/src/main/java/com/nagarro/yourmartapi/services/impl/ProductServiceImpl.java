package com.nagarro.yourmartapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.ProductDao;
import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.dto.ProductResp;
import com.nagarro.yourmartapi.dto.ProductStatus;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.services.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	public void addProduct(ProductDetails productDetails) {
		this.productDao.createNewProduct(productDetails);		
	}

	public Response<List<ProductResp>> fetchAllProducts(String sortBy, List<String> status, String searchKeyword,
			String searchType) {
		return this.productDao.getAllProducts(sortBy, status, searchKeyword, searchType);
	}

	public Response<ProductResp> fetchSingleProduct(Integer productId) {
		return this.productDao.getProductById(productId);
	}

	public Response<List<ProductResp>> fetchProductsOfSeller(Integer sellerId, String sortBy, List<String> status, Integer offset) {
		return this.productDao.getAllProductsOfSeller(sellerId, sortBy, status, offset);
	}

	public void updateProduct(Integer productId, ProductDetails productDetails) {
		this.productDao.updateProduct(productId, productDetails);
	}

	public Response<Product> updateProductStatus(Integer productId, ProductStatus productStatus) {
		return this.productDao.updateProductStatus(productId, productStatus);
	}
}
