package com.nagarro.YourmartAdmin.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.YourmartAdmin.dto.SellerListResponse;
import com.nagarro.YourmartAdmin.service.SellerService;

@Controller
public class ProfileController {
	
	@Autowired
	SellerService sellerService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView displayProfile(HttpServletRequest request) {
		
		SellerListResponse sellerList = sellerService.getAllSellers(null);
		
		ModelAndView mav = new ModelAndView("profile");
		mav.addObject("user",request.getParameter("username"));
		HttpSession session = request.getSession();
		session.setAttribute("user", request.getParameter("username"));
		mav.addObject("sellerList", sellerList.getPayload());
		return mav;
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView updateList(@RequestParam(value = "sortBy",required=false) List<String> sortBy, @RequestParam(value="status",required=false) String status,HttpServletRequest request) {
		HashMap<String, String> queryParams = new HashMap<>();
		
		if(!Objects.isNull(sortBy)) {
			for(String sortName: sortBy) {
				queryParams.put("sortBy", sortName);
			}
		}
		
		if(!Objects.isNull(status)) {
			queryParams.put("status", status);
		}
		
		SellerListResponse sellerList = sellerService.getAllSellers(queryParams);
		ModelAndView mav = new ModelAndView("profile");
		
		HttpSession session=request.getSession();
		
		mav.addObject("user",request.getAttribute("username"));
		mav.addObject("sellerList", sellerList.getPayload());
		return mav;
	}

}
