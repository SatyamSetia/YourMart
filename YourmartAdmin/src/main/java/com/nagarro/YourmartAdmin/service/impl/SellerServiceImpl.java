package com.nagarro.YourmartAdmin.service.impl;

import java.util.HashMap;
import java.util.Objects;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.YourmartAdmin.constants.Url;
import com.nagarro.YourmartAdmin.dto.SellerListResponse;
import com.nagarro.YourmartAdmin.service.SellerService;

public class SellerServiceImpl implements SellerService{

	public SellerListResponse getAllSellers(HashMap<String, String> queryParams) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Url.BASE_URL);
		
		target = target.path(Url.GET_ALL_SELLERS);
		if(!Objects.isNull(queryParams)) {
			for(String key: queryParams.keySet()) {
				String value = queryParams.get(key);
				target = target.queryParam(key, value);
			}
		}
		
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		SellerListResponse sellerList = response.readEntity(SellerListResponse.class);
		return sellerList;
	}
	
}
