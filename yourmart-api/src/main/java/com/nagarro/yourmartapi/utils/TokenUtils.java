package com.nagarro.yourmartapi.utils;

public class TokenUtils {
	
	public static String generateToken(String username, Integer id) {
		return "ym-"+username.hashCode()+"-"+id;
	}

}
