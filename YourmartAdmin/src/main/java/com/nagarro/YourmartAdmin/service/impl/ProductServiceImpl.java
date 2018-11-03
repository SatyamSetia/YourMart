package com.nagarro.YourmartAdmin.service.impl;

import java.util.HashMap;
import java.util.Objects;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.YourmartAdmin.constants.Url;
import com.nagarro.YourmartAdmin.dto.ProductListResponse;
import com.nagarro.YourmartAdmin.dto.ProductResponse;
import com.nagarro.YourmartAdmin.dto.SellerResponse;
import com.nagarro.YourmartAdmin.service.ProductService;

public class ProductServiceImpl implements ProductService {

	public ProductListResponse getAllProducts(HashMap<String, String> queryParams) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Url.BASE_URL);
		
		target = target.path(Url.GET_ALL_PRODUCTS);
		if(!Objects.isNull(queryParams)) {
			for(String key: queryParams.keySet()) {
				String value = queryParams.get(key);
				target = target.queryParam(value, key);
				System.out.println(key+" "+value);
			}
		}
		
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		ProductListResponse productList = response.readEntity(ProductListResponse.class);
		return productList;
	}

	public ProductResponse getProductById(int parseInt) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Url.BASE_URL);
		
		Response response = target.path(Url.GET_ALL_PRODUCTS+"/"+parseInt).request(MediaType.APPLICATION_JSON).get();
		
		ProductResponse product = response.readEntity(ProductResponse.class);
		return product;

	}
	
	

}
