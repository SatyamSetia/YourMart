package com.nagarro.yourmartapi.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constants.HqlQueries;
import com.nagarro.yourmartapi.dao.AdminDao;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Admin;
import com.nagarro.yourmartapi.utils.HibernateUtils;

@Component
public class AdminDaoImpl implements AdminDao{
	
	private Session session;
	
	public AdminDaoImpl() {
		this.session = HibernateUtils.createSession();
	}
	
	public Response<Admin> getAdmin(Admin admin) {
		
		Query<Object[]> query = this.session.createQuery(HqlQueries.SELECT_ADMIN_FROM_TABLE);
		query.setParameter("username", admin.getUsername());
		query.setParameter("password", admin.getPassword());
		List<Object[]> adminList = query.getResultList();
		
		Response<Admin> response;
		
		if(adminList.size()==0) {
			response = new Response<Admin>(401, null, "Invalid username or password");
			return response;
		}
		
		Admin adminAfterLogin = new Admin();
		adminAfterLogin.setAdminId((Integer) adminList.get(0)[0]);
		adminAfterLogin.setUsername((String) adminList.get(0)[1]);
		
		response = new Response<Admin>(200, adminAfterLogin, null);
		return response;
	}	

}
