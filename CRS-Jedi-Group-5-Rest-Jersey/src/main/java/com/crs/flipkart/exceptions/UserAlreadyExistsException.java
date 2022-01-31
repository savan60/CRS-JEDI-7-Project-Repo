package com.crs.flipkart.exceptions;

public class UserAlreadyExistsException extends Exception{
	
	private String email;
			
	public UserAlreadyExistsException(String email) {
		this.email=email;
	}
	
	public String getEmail(){
		return email;
	}
}
