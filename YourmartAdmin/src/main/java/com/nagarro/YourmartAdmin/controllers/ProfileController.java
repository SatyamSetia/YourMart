package com.nagarro.YourmartAdmin.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.YourmartAdmin.constants.ResponseStatus;
import com.nagarro.YourmartAdmin.dto.SellerListResponse;
import com.nagarro.YourmartAdmin.dto.SellerRespPayload;
import com.nagarro.YourmartAdmin.dto.SellerResponse;
import com.nagarro.YourmartAdmin.dto.SellerStatus;
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
	public ModelAndView updateList(@RequestParam(value = "sortBy",required=false) String sortBy, @RequestParam(value="status",required=false) List<String> status,HttpServletRequest request, @RequestParam(value="searchBy", required=false) String searchType, @RequestParam(value="keyword", required=false) String searchKeyword ) {
		
		HashMap<String, String> queryParams = new HashMap<>();
		
		if(!Objects.isNull(searchKeyword) && !Objects.isNull(searchType)) {
			queryParams.put(searchType, "searchBy");
			queryParams.put(searchKeyword, "keyword");
		}
		
		if(!Objects.isNull(status)) {
			for(String eachStatus: status) {
				System.out.println(eachStatus);
				queryParams.put(eachStatus,"status");
			}
		}
		
		if(!Objects.isNull(sortBy)) {
			queryParams.put(sortBy,"sortBy");
		}
		
		SellerListResponse sellerList = sellerService.getAllSellers(queryParams);
		ModelAndView mav = new ModelAndView("profile");
		
		HttpSession session=request.getSession();
		
		mav.addObject("user",request.getAttribute("username"));
		mav.addObject("sellerList", sellerList.getPayload());
		return mav;
	}
	
	@RequestMapping(value = "/approveAll")
	public ModelAndView updateAll(@RequestParam(value="check", required=false) List<Integer> sellerIdList,HttpServletRequest request) {
			
		HttpSession session=request.getSession();
		
		if(Objects.isNull(sellerIdList)) {
			SellerListResponse sellerList = sellerService.getAllSellers(null);
			
			ModelAndView mav = new ModelAndView("profile");
			mav.addObject("user",request.getAttribute("username"));
			mav.addObject("sellerList", sellerList.getPayload());
			
			return mav;
		}
		
		SellerListResponse sellerList = sellerService.updateMultipleSellers(sellerIdList, request.getAttribute("token")+"");
		
		ModelAndView mav;
		
		if(sellerList.getStatus() != ResponseStatus.SUCCESS_CODE) {
			mav = new ModelAndView("profile");
			mav.addObject("listError", "OOPS!! Error Fetching list.");
			return mav;
		}
		
		mav = new ModelAndView("profile");
		
		mav.addObject("user",request.getAttribute("username"));
		mav.addObject("sellerList", sellerList.getPayload());
		
		return mav;
	}
	
	@RequestMapping(value="/seller/{sellerId}")
	public ModelAndView displaySellerPage(@PathVariable("sellerId") String sellerId) {
		
		SellerResponse seller = new SellerResponse();
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName("sellerDetail");
		try {
			seller = sellerService.getSellerById(Integer.parseInt(sellerId));
		} catch(NumberFormatException exception) {
			mav.addObject("error", "OOPS!! Seller id does not exists.");
			return mav;
		}
		
		if(seller.getStatus() != ResponseStatus.SUCCESS_CODE) {
			mav = new ModelAndView("sellerDetail");
			mav.addObject("listError", seller.getError());
			return mav;
		}
		
		mav.addObject("seller", seller.getPayload());
		return mav;
	}
	
	@RequestMapping(value="/seller/changeStatus")
	public void changeStatus(@RequestParam(value="sellerId") String sellerId, @RequestParam(value="newStatus") String newStatus, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession(); 
		
		SellerStatus seller = new SellerStatus(Integer.parseInt(sellerId), newStatus);
		
		sellerService.updateSeller(seller, request.getAttribute("token")+"");
		
		try {
			response.sendRedirect("/YourmartAdmin/seller/"+sellerId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
