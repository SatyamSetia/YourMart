package com.nagarro.YourmartAdmin.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.YourmartAdmin.constants.ResponseStatus;
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
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("authenticate") UserLogin adminBeforeLogin) {
		
		LoginResponse adminAfterLogin = authenticationService.authenticate(adminBeforeLogin);
		ModelAndView mav;
		
		if(adminAfterLogin.getStatus() != ResponseStatus.SUCCESS_CODE) {
			mav = new ModelAndView("index");
			mav.addObject("error", adminAfterLogin.getError());
			return mav;
		}
		mav = new ModelAndView();
		mav.addObject("username", adminAfterLogin.getPayload().getUsername());
		HttpSession session = request.getSession();
		session.setAttribute("token", adminAfterLogin.getPayload().getToken());
		mav.setViewName("redirect:/home");
		return mav;
	}

}
