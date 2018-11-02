package com.nagarro.yourmartapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.ProductDao;
import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.services.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public void addProduct(ProductDetails productDetails) {
		this.productDao.createNewProduct(productDetails);		
	}
}
