package com.crs.flipkart.exceptions;
/**
 * 
 * 
 * 
 * @author nikhil.kumar3
 *
 * Raised when user is not in the database
 */
public class UserNotFoundException extends Exception {
	
	private String userCredential;
	public UserNotFoundException(String userCredential) {
		this.userCredential = userCredential;
	}
	public String getuserCredential() {
		return userCredential;
	}
}
