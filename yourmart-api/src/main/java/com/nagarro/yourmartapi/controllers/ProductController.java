package com.nagarro.yourmartapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.dto.ProductResp;
import com.nagarro.yourmartapi.dto.ProductStatus;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.services.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public void addProduct(@RequestBody ProductDetails productDetails) {
		this.productService.addProduct(productDetails);		
	}
	
	@GetMapping("/products")
	public Response<List<ProductResp>> fetchAllProducts(@RequestParam(value="sortBy", required=false) String sortBy, @RequestParam(value="status", required=false) List<String> status, @RequestParam(value="searchBy", required=false) String searchType, @RequestParam(value="keyword", required=false) String searchKeyword) {
		return this.productService.fetchAllProducts(sortBy,status, searchKeyword, searchType);
	}
	
	@GetMapping("/products/{productId}")
	public Response<ProductResp> fetchSinglePrduct(@PathVariable(value="productId") Integer productId) {
		return this.productService.fetchSingleProduct(productId);
	}
	
	@GetMapping("/products/seller/{sellerId}")
	public Response<List<ProductResp>> fetchProductsOfSeller(@PathVariable(value="sellerId") Integer sellerId, @RequestParam(value="sortBy", required=false) String sortBy, @RequestParam(value="status", required=false) List<String> status,@RequestParam(value="offset", required=false) Integer offset) {
		return this.productService.fetchProductsOfSeller(sellerId,sortBy,status,offset);
	}
	
	@PutMapping("/products/{productId}")
	public void updateProduct(@PathVariable(value="productId") Integer productId, @RequestBody ProductDetails productDetails) {
		this.productService.updateProduct(productId, productDetails);
	}
	
	@PutMapping("/products/status/{productId}")
	public Response<Product> updateProductStatus(@PathVariable(value="productId") Integer productId,@RequestBody ProductStatus productStatus) {
		return this.productService.updateProductStatus(productId,productStatus);
	}
}
