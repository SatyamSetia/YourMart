package com.nagarro.YourmartAdmin.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.YourmartAdmin.constants.ResponseStatus;
import com.nagarro.YourmartAdmin.dto.ProductListResponse;
import com.nagarro.YourmartAdmin.dto.ProductRespPayload;
import com.nagarro.YourmartAdmin.dto.ProductResponse;
import com.nagarro.YourmartAdmin.dto.ProductStatus;
import com.nagarro.YourmartAdmin.dto.SellerResponse;
import com.nagarro.YourmartAdmin.dto.UserLogin;
import com.nagarro.YourmartAdmin.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/products")
	public ModelAndView displayProductListPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		ProductListResponse products = productService.getAllProducts(null);
		
		HttpSession session=request.getSession();
		mav.setViewName("productList");
		mav.addObject("user", request.getAttribute("username"));
		mav.addObject("productList", products.getPayload());
		return mav;
	}
	
	@RequestMapping(value="/searchProducts")
	public ModelAndView updateProductList(HttpServletRequest request, @RequestParam(value = "sortBy",required=false) String sortBy, @RequestParam(value="status",required=false) List<String> status, @RequestParam(value="searchBy", required=false) String searchType, @RequestParam(value="keyword", required=false) String searchKeyword) {
		
		HashMap<String, String> queryParams = new HashMap<>();
		ModelAndView mav = new ModelAndView();
		
		if(!Objects.isNull(searchKeyword) && !Objects.isNull(searchType)) {
			queryParams.put(searchType, "searchBy");
			queryParams.put(searchKeyword, "keyword");
		}
		
		if(!Objects.isNull(status)) {
			for(String eachStatus: status) {
				queryParams.put(eachStatus,"status");
			}
		}
		
		if(!Objects.isNull(sortBy)) {
			queryParams.put(sortBy,"sortBy");
		}
		
		ProductListResponse products = productService.getAllProducts(queryParams);
		
		HttpSession session=request.getSession();
		mav.setViewName("productList");
		mav.addObject("user", request.getAttribute("username"));
		mav.addObject("productList", products.getPayload());
		return mav;
	}
	
	@RequestMapping(value="/products/{productId}")
	public ModelAndView displaySellerPage(@PathVariable("productId") String productId) {
		
		ProductResponse product = new ProductResponse();
		
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName("productDetail");
		try {
			product = productService.getProductById(Integer.parseInt(productId));
		} catch(NumberFormatException exception) {
			mav.addObject("error", "OOPS!! Seller id does not exists.");
			return mav;
		}
		
		if(product.getStatus() != ResponseStatus.SUCCESS_CODE) {
			mav = new ModelAndView("productDetails");
			mav.addObject("listError", product.getError());
			return mav;
		}
		
		mav.addObject("product", product.getPayload());
		return mav;
	}
	
	@RequestMapping(value="/updateStatus", method = RequestMethod.POST)
	public void updateProductStatus(@ModelAttribute("updateStatus") ProductStatus productStatus, HttpServletResponse response) {
		
		this.productService.updateProductStatus(productStatus);
		try {
			response.sendRedirect("/YourmartAdmin/home");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
