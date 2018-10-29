package com.nagarro.yourmartapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.services.SellerService;

@RestController
public class SellerControler {
	
	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/seller/register")
	public Response<LoginSignupResp> authenticateAdmin(@RequestBody RegisterSeller sellerBeforeRegistration) {
		return this.sellerService.registerSeller(sellerBeforeRegistration);		
	}

}
