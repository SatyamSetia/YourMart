package com.nagarro.yourmartapi.services;

import java.util.List;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.SellerResp;
import com.nagarro.yourmartapi.dto.SellerStatus;
import com.nagarro.yourmartapi.models.Seller;

public interface SellerService {
	
	public Response<LoginSignupResp> registerSeller(RegisterSeller sellerBeforeRegistration);

	public Response<LoginSignupResp> authenticateSeller(Seller sellerBeforeLogin);

	public Response<List<SellerResp>> updateSellerStatus(List<SellerStatus> sellerStatusList);

	public Response<List<SellerResp>> fetchAllSellers(List<String> sortBy, String status, String searchKeyword, String searchType);

	public Response<SellerResp> fetchSellerById(Integer sellerId);

}
