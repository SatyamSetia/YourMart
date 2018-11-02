package com.nagarro.YourmartAdmin.service;

import java.util.HashMap;
import java.util.List;

import com.nagarro.YourmartAdmin.dto.SellerListResponse;
import com.nagarro.YourmartAdmin.dto.SellerRespPayload;
import com.nagarro.YourmartAdmin.dto.SellerResponse;
import com.nagarro.YourmartAdmin.dto.SellerStatus;

public interface SellerService {
	
	SellerListResponse getAllSellers(HashMap<String, String> queryParams);
	
	SellerListResponse updateMultipleSellers(List<Integer> sellerIdList, String token);

	SellerResponse getSellerById(int parseInt);
	
	SellerListResponse updateSeller(SellerStatus seller, String token);

}
