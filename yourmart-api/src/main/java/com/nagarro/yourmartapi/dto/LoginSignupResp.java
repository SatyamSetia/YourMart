package com.nagarro.yourmartapi.dto;

public class LoginSignupResp {
	
	private Integer id;
	
	private String username;
	
	private String token;
	
	public LoginSignupResp(Integer id, String username, String token) {
		this.id = id;
		this.username = username;
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
