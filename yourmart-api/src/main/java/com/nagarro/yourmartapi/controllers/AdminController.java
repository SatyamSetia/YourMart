package com.nagarro.yourmartapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Admin;
import com.nagarro.yourmartapi.services.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin/login")
	public Response<Admin> authenticateAdmin(@RequestBody Admin admin) {
		
		return adminService.authenticate(admin);
		
	}
}
