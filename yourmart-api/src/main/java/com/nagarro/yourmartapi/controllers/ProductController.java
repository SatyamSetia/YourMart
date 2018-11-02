package com.nagarro.yourmartapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public void addProduct(@RequestBody ProductDetails productDetails) {
		//System.out.println(productDetails.getProdAttr().length);
		this.productService.addProduct(productDetails);		
	}
}
