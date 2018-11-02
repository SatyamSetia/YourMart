package com.nagarro.yourmartapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.dto.ProductResp;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public void addProduct(@RequestBody ProductDetails productDetails) {
		//System.out.println(productDetails.getProdAttr().length);
		this.productService.addProduct(productDetails);		
	}
	
	@GetMapping("/products")
	public Response<List<ProductResp>> fetchAllProducts(@RequestParam(value="sortBy", required=false) List<String> sortBy, @RequestParam(value="status", required=false) String status, @RequestParam(value="searchBy", required=false) String searchType, @RequestParam(value="keyword", required=false) String searchKeyword) {
		return this.productService.fetchAllProducts(sortBy,status, searchKeyword, searchType);
	}
}
