package com.nagarro.yourmartapi.services;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.SellerStatus;
import com.nagarro.yourmartapi.models.Seller;

public interface SellerService {
	
	public Response<LoginSignupResp> registerSeller(RegisterSeller sellerBeforeRegistration);

	public Response<LoginSignupResp> authenticateSeller(Seller sellerBeforeLogin);

	public Response<String> updateSellerStatus(Integer sellerId, SellerStatus sellerStatus);

}
