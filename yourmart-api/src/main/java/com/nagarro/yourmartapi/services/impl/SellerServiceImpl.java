package com.nagarro.yourmartapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.SellerDao;
import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.services.SellerService;

@Component
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerDao sellerDao;

	public Response<LoginSignupResp> registerSeller(RegisterSeller sellerBeforeRegistration) {
		return this.sellerDao.createSeller(sellerBeforeRegistration);
	}

}
