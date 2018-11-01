package com.nagarro.YourmartAdmin.service;

import com.nagarro.YourmartAdmin.dto.LoginResponse;
import com.nagarro.YourmartAdmin.dto.UserLogin;

public interface AuthenticationService {
	
	LoginResponse authenticate(UserLogin user);

}
