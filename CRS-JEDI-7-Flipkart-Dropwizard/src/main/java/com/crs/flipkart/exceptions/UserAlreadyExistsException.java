package com.crs.flipkart.exceptions;

/**
 * 
 * 
 * 
 * @author nikhil.kumar3
 * 
 * Raised when user already registered tries to register again
 */
public class UserAlreadyExistsException extends Exception{
	
	private String email;
			
	public UserAlreadyExistsException(String email) {
		this.email=email;
	}
	
	public String getEmail(){
		return email;
	}
}
