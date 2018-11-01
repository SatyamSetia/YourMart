package com.nagarro.YourmartAdmin.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.YourmartAdmin.dto.LoginRespPayload;
import com.nagarro.YourmartAdmin.dto.LoginResponse;
import com.nagarro.YourmartAdmin.dto.UserLogin;
import com.nagarro.YourmartAdmin.service.AuthenticationService;


@Controller
public class AuthenticationController {
	
	@Autowired
	AuthenticationService authenticationService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView loginProcess(@ModelAttribute("authenticate") UserLogin adminBeforeLogin) {
		
		LoginResponse adminAfterLogin = authenticationService.authenticate(adminBeforeLogin);
		
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("user", adminAfterLogin.getPayload().getUsername());
		
		return mav;
	}

}
