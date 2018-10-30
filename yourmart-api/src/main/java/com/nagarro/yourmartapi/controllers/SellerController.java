package com.nagarro.yourmartapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.SellerStatus;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.services.SellerService;

@RestController
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/seller/register")
	public Response<LoginSignupResp> registerSeller(@RequestBody RegisterSeller sellerBeforeRegistration) {
		return this.sellerService.registerSeller(sellerBeforeRegistration);		
	}
	
	@PostMapping("/seller/login")
	public Response<LoginSignupResp> authenticateSeller(@RequestBody Seller sellerBeforeLogin) {
		return this.sellerService.authenticateSeller(sellerBeforeLogin);
	}
	
	@PutMapping("/seller/{sellerId}/status")
	public Response<String> updateSellerStatus(@PathVariable Integer sellerId, @RequestBody SellerStatus sellerStatus, @RequestHeader(value="token") String token) {
		return this.sellerService.updateSellerStatus(sellerId,sellerStatus);
	}

}
