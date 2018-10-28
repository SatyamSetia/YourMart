package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Admin;

public interface AdminDao {
	
	public Response<Admin> getAdmin(Admin admin);

}
