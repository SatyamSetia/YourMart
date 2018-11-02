package com.nagarro.YourmartAdmin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.YourmartAdmin.constants.Url;
import com.nagarro.YourmartAdmin.dto.SellerListResponse;
import com.nagarro.YourmartAdmin.dto.SellerRespPayload;
import com.nagarro.YourmartAdmin.dto.SellerResponse;
import com.nagarro.YourmartAdmin.dto.SellerStatus;
import com.nagarro.YourmartAdmin.service.SellerService;

public class SellerServiceImpl implements SellerService{

	public SellerListResponse getAllSellers(HashMap<String, String> queryParams) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Url.BASE_URL);
		
		target = target.path(Url.GET_ALL_SELLERS);
		if(!Objects.isNull(queryParams)) {
			for(String key: queryParams.keySet()) {
				String value = queryParams.get(key);
				target = target.queryParam(value, key);
				System.out.println(key+" "+value);
			}
		}
		
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		SellerListResponse sellerList = response.readEntity(SellerListResponse.class);
		return sellerList;
	}

	public SellerListResponse updateMultipleSellers(List<Integer> sellerIdList, String token) {
		
		List<SellerStatus> list = new ArrayList<SellerStatus>();
		
		for(Integer sellerId: sellerIdList) {
			list.add(new SellerStatus(sellerId,"APPROVED"));
		}
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Url.BASE_URL);
		Response response = target.path(Url.GET_ALL_SELLERS).request(MediaType.APPLICATION_JSON).header("token", token).put(Entity.entity(list, MediaType.APPLICATION_JSON));
		
		SellerListResponse sellerList = response.readEntity(SellerListResponse.class);
		
		return sellerList;
	}
	
	public SellerListResponse updateSeller(SellerStatus seller, String token) {
		
		List<SellerStatus> list = new ArrayList<SellerStatus>();
		
		list.add(seller);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Url.BASE_URL);
		Response response = target.path(Url.GET_ALL_SELLERS).request(MediaType.APPLICATION_JSON).header("token", token).put(Entity.entity(list, MediaType.APPLICATION_JSON));
		
		SellerListResponse sellerList = response.readEntity(SellerListResponse.class);
		
		return sellerList;
		
	}

	public SellerResponse getSellerById(int parseInt) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Url.BASE_URL);
		
		Response response = target.path(Url.GET_ALL_SELLERS+"/"+parseInt).request(MediaType.APPLICATION_JSON).get();
		
		SellerResponse seller = response.readEntity(SellerResponse.class);
		return seller;
	}
	
}
