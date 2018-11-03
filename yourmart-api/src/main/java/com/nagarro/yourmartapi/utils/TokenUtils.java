package com.nagarro.yourmartapi.utils;

public class TokenUtils {
	
	public static String generateToken(String username, Integer id) {
		return "ym-"+username.hashCode()+"#"+id;
	}

	public static Integer getIdFromToken(String token) {
		Integer id;
		try {
			id = Integer.parseInt(token.split("#")[1]);
		} catch(Exception exp) {
			return null;
		}
		return id;
	}

}
