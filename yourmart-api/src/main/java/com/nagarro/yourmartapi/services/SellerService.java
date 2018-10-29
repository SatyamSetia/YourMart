package com.nagarro.yourmartapi.services;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;

public interface SellerService {
	
	public Response<LoginSignupResp> registerSeller(RegisterSeller sellerBeforeRegistration);

}
