package com.nagarro.yourmartapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.SellerDao;
import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.SellerResp;
import com.nagarro.yourmartapi.dto.SellerStatus;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.services.SellerService;

@Component
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerDao sellerDao;

	public Response<LoginSignupResp> registerSeller(RegisterSeller sellerBeforeRegistration) {
		return this.sellerDao.createSeller(sellerBeforeRegistration);
	}
	
	public Response<LoginSignupResp> authenticateSeller(Seller sellerBeforeLogin) {
		return this.sellerDao.getSeller(sellerBeforeLogin);
	}

	public Response<String> updateSellerStatus(List<SellerStatus> sellerStatusList) {
		return this.sellerDao.updateSellerStatus(sellerStatusList);
	}

	public Response<List<SellerResp>> fetchAllSellers(List<String> sortBy, String status) {
		return this.sellerDao.getAllSellers(sortBy, status);
	}

	public Response<SellerResp> fetchSellerById(Integer sellerId) {
		return this.sellerDao.getSellerById(sellerId);
	}

}
