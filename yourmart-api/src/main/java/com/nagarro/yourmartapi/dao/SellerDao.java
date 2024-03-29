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

	public Response<List<SellerResp>> updateSellerStatus(List<SellerStatus> sellerStatusList);

	public Response<List<SellerResp>> getAllSellers(String sortBy, List<String> status, String searchKeyword, String searchType);

	public Response<SellerResp> getSellerById(Integer SellerId);

	public Response<SellerResp> getCurrentUser(String token);

}
