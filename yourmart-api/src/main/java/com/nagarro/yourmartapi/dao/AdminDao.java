package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Admin;

public interface AdminDao {
	
	public Response<LoginSignupResp> getAdmin(Admin admin);

}
