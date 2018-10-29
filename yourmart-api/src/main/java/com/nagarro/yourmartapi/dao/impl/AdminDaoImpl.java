package com.nagarro.yourmartapi.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constants.HqlQueries;
import com.nagarro.yourmartapi.dao.AdminDao;
import com.nagarro.yourmartapi.dto.LoginSignupResp;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Admin;
import com.nagarro.yourmartapi.utils.HibernateUtils;
import com.nagarro.yourmartapi.utils.TokenUtils;

@Component
public class AdminDaoImpl implements AdminDao{
	
	private Session session;
	
	public AdminDaoImpl() {
		this.session = HibernateUtils.createSession();
	}
	
	public Response<LoginSignupResp> getAdmin(Admin admin) {
		
		Query query = this.session.createQuery(HqlQueries.SELECT_ADMIN_FROM_TABLE);
		query.setParameter("username", admin.getUsername());
		query.setParameter("password", admin.getPassword());
		List<Object[]> adminList = query.list();
		
		Response<LoginSignupResp> response;
		
		if(adminList.size()==0) {
			response = new Response<LoginSignupResp>(401, null, "Invalid username or password");
			return response;
		}
		
		String token = TokenUtils.generateToken((String) adminList.get(0)[1], (Integer) adminList.get(0)[0]);
		
		LoginSignupResp adminAfterLogin = new LoginSignupResp((Integer) adminList.get(0)[0], (String) adminList.get(0)[1], token);
		
		response = new Response<LoginSignupResp>(200, adminAfterLogin, null);
		return response;
	}	

}
