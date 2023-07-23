package com.spring.BlogApp.Payloads;

import com.spring.BlogApp.Dto.UserDto;


public class JwtResponse {

	private String token;
	private UserDto userDto;
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponse(String token, UserDto userDto) {
		super();
		this.token = token;
		this.userDto = userDto;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
}
