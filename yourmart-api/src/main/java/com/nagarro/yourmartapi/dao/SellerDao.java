package com.nagarro.yourmartapi.dao;

import java.util.List;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.SellerResp;
import com.nagarro.yourmartapi.dto.SellerStatus;
import com.nagarro.yourmartapi.models.Seller;

public interface SellerDao {
	
	public Response<LoginSignupResp> createSeller(RegisterSeller sellerBeforeRegistration);

	public Response<LoginSignupResp> getSeller(Seller sellerBeforeLogin);

	public Response<String> updateSellerStatus(Integer sellerId, SellerStatus sellerStatus);

	public Response<List<SellerResp>> getAllSellers(List<String> sortBy, String status);

}
