package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Seller;

public interface SellerDao {
	
	public Response<LoginSignupResp> createSeller(RegisterSeller sellerBeforeRegistration);

	public Response<LoginSignupResp> getSeller(Seller sellerBeforeLogin);

}
