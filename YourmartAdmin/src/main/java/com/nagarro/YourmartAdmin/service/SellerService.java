package com.nagarro.YourmartAdmin.service;

import java.util.HashMap;

import com.nagarro.YourmartAdmin.dto.SellerListResponse;

public interface SellerService {
	
	SellerListResponse getAllSellers(HashMap<String, String> queryParams);

}
