package com.nagarro.yourmartapi.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constants.Status;
import com.nagarro.yourmartapi.dao.SellerDao;
import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.models.SellerDetails;
import com.nagarro.yourmartapi.services.SellerService;
import com.nagarro.yourmartapi.utils.HibernateUtils;
import com.nagarro.yourmartapi.utils.TokenUtils;

@Component
public class SellerDaoImpl implements SellerDao {
	
	private Session session;
	
	public SellerDaoImpl() {
		this.session = HibernateUtils.createSession();
	}

	public Response<LoginSignupResp> createSeller(RegisterSeller sellerBeforeRegistration) {
		
		Seller seller = new Seller();
		seller.setUsername(sellerBeforeRegistration.getUsername());
		seller.setPassword(sellerBeforeRegistration.getPassword());
		seller.setStatus(Status.NEED_APPROVAL);
		
		SellerDetails sellerDetails = new SellerDetails();
		
		sellerDetails.setAddress(sellerBeforeRegistration.getAddress());
		sellerDetails.setCompany(sellerBeforeRegistration.getCompany());
		sellerDetails.setEmail(sellerBeforeRegistration.getEmail());
		sellerDetails.setGstNumber(sellerBeforeRegistration.getGstNumber());
		sellerDetails.setOwnerName(sellerBeforeRegistration.getOwnerName());
		sellerDetails.setTelephone(sellerBeforeRegistration.getTelephone());
		
		Response<LoginSignupResp> response;
		this.session.beginTransaction();
		
		try {
			
			int id = (int) session.save(seller);
			sellerDetails.setSellerId(id);
			
			session.save(sellerDetails);
			
			this.session.getTransaction().commit();
			
			String token = TokenUtils.generateToken(seller.getUsername(), seller.getSellerId());
			
			LoginSignupResp resp = new LoginSignupResp(id, seller.getUsername(),token);
			response = new Response<LoginSignupResp>(200,resp,null);
			
		} catch(Exception exp) {
			System.out.println(exp.getMessage());
			response = new Response<LoginSignupResp>(403,null,exp.getMessage());
		}
		session.close();
		
		return response;
	}

}
