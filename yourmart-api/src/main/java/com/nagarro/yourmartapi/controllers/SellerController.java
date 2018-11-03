package com.nagarro.yourmartapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.SellerResp;
import com.nagarro.yourmartapi.dto.SellerStatus;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.services.SellerService;

@RestController
@CrossOrigin
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/sellers/register")
	public Response<LoginSignupResp> registerSeller(@RequestBody RegisterSeller sellerBeforeRegistration) {
		return this.sellerService.registerSeller(sellerBeforeRegistration);		
	}
	
	@PostMapping("/sellers/login")
	public Response<LoginSignupResp> authenticateSeller(@RequestBody Seller sellerBeforeLogin) {
		return this.sellerService.authenticateSeller(sellerBeforeLogin);
	}
	
	@PutMapping("/sellers")
	public Response<List<SellerResp>> updateSellerStatus(@RequestBody List<SellerStatus> sellerStatusList, @RequestHeader(value="token") String token) {
		return this.sellerService.updateSellerStatus(sellerStatusList);
	}
	
	@GetMapping("/sellers")
	public Response<List<SellerResp>> fetchAllSellers(@RequestParam(value="sortBy", required=false) String sortBy, @RequestParam(value="status", required=false) List<String> status, @RequestParam(value="searchBy", required=false) String searchType, @RequestParam(value="keyword", required=false) String searchKeyword) {
		System.out.println(searchKeyword+" "+searchType);
		return this.sellerService.fetchAllSellers(sortBy,status, searchKeyword, searchType);
	}
	
	@GetMapping("/sellers/{sellerId}")
	public Response<SellerResp> fetchSellerById(@PathVariable Integer sellerId) {
		return this.sellerService.fetchSellerById(sellerId);
	}
	
	@GetMapping("/sellers/user")
	public Response<SellerResp> fetchCurrentUser(@RequestHeader(value="token") String token) {
		return this.sellerService.fetchCurrentUser(token);
	}

}
