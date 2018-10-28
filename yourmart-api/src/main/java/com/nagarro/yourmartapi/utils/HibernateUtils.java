package com.nagarro.yourmartapi.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.yourmartapi.constants.Keys;

public class HibernateUtils {
	
	public static Session createSession() {
		SessionFactory sf=new Configuration().configure(Keys.HIBERNATE_CONFIG_FILE_PATH).buildSessionFactory();
		Session session= sf.openSession();
		return session;
	}

}
