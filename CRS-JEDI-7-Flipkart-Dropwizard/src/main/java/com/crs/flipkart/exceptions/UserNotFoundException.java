package com.crs.flipkart.exceptions;

public class UserNotFoundException extends Exception {
	
	private String userCredential;
	public UserNotFoundException(String userCredential) {
		this.userCredential = userCredential;
	}
	public String getuserCredential() {
		return userCredential;
	}
}
