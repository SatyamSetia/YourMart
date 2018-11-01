package com.nagarro.YourmartAdmin.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.YourmartAdmin.constants.Url;
import com.nagarro.YourmartAdmin.dto.LoginResponse;
import com.nagarro.YourmartAdmin.dto.UserLogin;
import com.nagarro.YourmartAdmin.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

	public LoginResponse authenticate(UserLogin user) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Url.BASE_URL);
		Response response = target.path(Url.ADMIN_AUTHENTICATION_ROUTE).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(user, MediaType.APPLICATION_JSON));
		
		LoginResponse loggedInUser = response.readEntity(LoginResponse.class);
		return loggedInUser;
	}
	
}
