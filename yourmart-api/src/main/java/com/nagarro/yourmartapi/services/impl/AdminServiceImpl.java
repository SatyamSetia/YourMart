package com.nagarro.yourmartapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.AdminDao;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Admin;
import com.nagarro.yourmartapi.services.AdminService;

@Component
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public Response<Admin> authenticate(Admin admin) {
		return adminDao.getAdmin(admin);
	}

}
