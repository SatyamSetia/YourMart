package com.nagarro.yourmartapi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constants.HqlQueries;
import com.nagarro.yourmartapi.constants.ResponseCodesAndMessages;
import com.nagarro.yourmartapi.constants.Status;
import com.nagarro.yourmartapi.dao.SellerDao;
import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.RegisterSeller;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.models.SellerDetails;
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
		seller.setStatus(Status.NEED_APPROVAL_STATUS);
		
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
	

	public Response<LoginSignupResp> getSeller(Seller sellerBeforeLogin) {
		
		Query query = this.session.createQuery(HqlQueries.SELECT_SELLER_FROM_TABLE);
		query.setParameter("username", sellerBeforeLogin.getUsername());
		query.setParameter("password", sellerBeforeLogin.getPassword());
		List<Object[]> sellerList = query.list();
		
		Response<LoginSignupResp> response;
		
		if(sellerList.size()==0) {
			response = new Response<LoginSignupResp>(ResponseCodesAndMessages.UNAUTHORIZED, null, ResponseCodesAndMessages.UNAUTHORIZED_MESSAGE);
			return response;
		}
		
		Integer id = (Integer) sellerList.get(0)[0];
		String username = (String) sellerList.get(0)[1];
		String status = (String) sellerList.get(0)[2];
		System.out.println(status);
		String token = TokenUtils.generateToken(username, id);
		
		if(status.equals(Status.APPROVED_STATUS)) {
			
			LoginSignupResp sellerAfterLogin = new LoginSignupResp(id, username, token);
			response = new Response<LoginSignupResp>(ResponseCodesAndMessages.SUCCESS, sellerAfterLogin, null);
			
		} else if(status.equals((Status.NEED_APPROVAL_STATUS))) {
			
			response = new Response<LoginSignupResp>(ResponseCodesAndMessages.FORBIDDEN, null, ResponseCodesAndMessages.NEED_APPROVAL_MESSAGE);
		
		} else {
			
			response = new Response<LoginSignupResp>(ResponseCodesAndMessages.FORBIDDEN, null, ResponseCodesAndMessages.REJECTED_MESSAGE);
			
		}
		
		return response;
	}

}
