package com.nagarro.yourmartapi.services;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Admin;

public interface AdminService {
	
	public Response<Admin> authenticate(Admin admin);

}
