package com.nagarro.yourmartapi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import com.nagarro.yourmartapi.dto.SellerResp;
import com.nagarro.yourmartapi.dto.SellerStatus;
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
			
			sellerDetails.setSeller(seller);
			
			session.save(sellerDetails);
			
			this.session.getTransaction().commit();
			
			String token = TokenUtils.generateToken(seller.getUsername(), seller.getSellerId());
			
			LoginSignupResp resp = new LoginSignupResp(seller.getSellerId(), seller.getUsername(),token);
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


	public Response<List<SellerResp>> updateSellerStatus(List<SellerStatus> sellerStatusList) {
		
		for(SellerStatus sellerStatus: sellerStatusList) {
			Seller seller = (Seller) this.session.load(Seller.class,sellerStatus.getSellerId());
			seller.setStatus(sellerStatus.getStatus());
			
			this.session.beginTransaction();
			
			try {
				this.session.getTransaction().commit();
			} catch(Exception exp) {
				System.out.println(exp);
			}
		}
		
		return getAllSellers(null, null, null, null);
	}

	public Response<List<SellerResp>> getAllSellers(String sortBy, List<String> status, String searchKeyword, String searchType) {
		
		String whereClause = "";
		String sortOrder = " ORDER BY FIELD(dbSellerDetails.seller.status, 'NEED_APPROVAL','APPROVED','REJECTED')";
		
		if(!Objects.isNull(sortBy)) {
			sortOrder = " ORDER BY dbSellerDetails.seller."+sortBy;
		}
		
		if(!Objects.isNull(status)) {
			whereClause = " WHERE dbSellerDetails.seller.status IN (";
			
			int count=0;
			for(String eachStatus: status) {
				System.out.println(eachStatus);
				if(count!=0) {
					whereClause += ", ";
				}
				whereClause += "'"+eachStatus+"'";
				count++;
			}
			
			whereClause += ")";
		}
		
		if(!Objects.isNull(searchKeyword) && !Objects.isNull(searchType)) {
			System.out.println("hii");
			sortOrder = "";
			whereClause = " WHERE dbSellerDetails."+searchType+" LIKE '%"+searchKeyword+"%'";
		}
		
		Query query = this.session.createQuery(HqlQueries.SELECT_SELLERS_FROM_TABLE + whereClause + sortOrder);
		
		List<SellerDetails> list = query.list();
		
		List<SellerResp> sellerList = new ArrayList<SellerResp>();
		Response<List<SellerResp>> response;
		
		for(SellerDetails seller: list) {
			SellerResp sellerResp = new SellerResp();
			
			sellerResp.setUsername(seller.getSeller().getUsername());
			sellerResp.setSellerId(seller.getSeller().getSellerId());
			sellerResp.setStatus(seller.getSeller().getStatus());
			sellerResp.setAddress(seller.getAddress());
			sellerResp.setCompany(seller.getCompany());
			sellerResp.setEmail(seller.getEmail());
			sellerResp.setGstNumber(seller.getGstNumber());
			sellerResp.setOwnerName(seller.getOwnerName());
			sellerResp.setTelephone(seller.getTelephone());
			
			sellerList.add(sellerResp);
		}
		
		response = new Response<List<SellerResp>>(ResponseCodesAndMessages.SUCCESS, sellerList, null);
		
		return response;
	}


	public Response<SellerResp> getSellerById(Integer sellerId) {
		
		Query query = this.session.createQuery(HqlQueries.SELECT_SELLER_BY_ID_FROM_TABLE);
		query.setParameter("sellerId", sellerId);
		
		List<SellerDetails> sellerList = new ArrayList<SellerDetails>();
		
		try {
			sellerList = query.list();
		} catch(Exception exp) {
			Response<SellerResp> response = new Response<>(ResponseCodesAndMessages.UNAUTHORIZED,null,exp.getMessage());
			return response;
		}
		
		if(sellerList.isEmpty()) {
			Response<SellerResp> response = new Response<>(ResponseCodesAndMessages.NOT_FOUND,null,"Seller does not exists.");
			return response;
		}
		
		SellerDetails seller = sellerList.get(0);
		
		SellerResp sellerResp = new SellerResp();
		
		sellerResp.setUsername(seller.getSeller().getUsername());
		sellerResp.setSellerId(seller.getSeller().getSellerId());
		sellerResp.setAddress(seller.getAddress());
		sellerResp.setCompany(seller.getCompany());
		sellerResp.setEmail(seller.getEmail());
		sellerResp.setGstNumber(seller.getGstNumber());
		sellerResp.setOwnerName(seller.getOwnerName());
		sellerResp.setTelephone(seller.getTelephone());
		sellerResp.setStatus(seller.getSeller().getStatus());
		
		Response<SellerResp> response = new Response<SellerResp>(200, sellerResp, null);
		
		return response;
	}


	public Response<SellerResp> getCurrentUser(String token) {
		
		Integer sellerId = TokenUtils.getIdFromToken(token);
		
		if(sellerId==null) {
			Response<SellerResp> response = new Response<>(ResponseCodesAndMessages.UNAUTHORIZED, null, "Not Logged in!!");
			return response;
		}
		return getSellerById(sellerId);
	}

}
