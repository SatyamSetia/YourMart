package com.nagarro.yourmartapi.constants;

public class ResponseCodesAndMessages {
	
	private ResponseCodesAndMessages() {
		
	}
	
	public static final int SUCCESS = 200;
	
	public static final int UNAUTHORIZED = 401;
	
	public static final int FORBIDDEN = 403;
	
	public static final int NOT_FOUND = 404;
	
	public static final String UNAUTHORIZED_MESSAGE = "Invalid username or password";
	
	public static final String NEED_APPROVAL_MESSAGE = "Your profile has not been approved yet.";
	
	public static final String REJECTED_MESSAGE = "Your profile has not been rejected.";

}
