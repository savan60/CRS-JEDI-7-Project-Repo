package com.crs.flipkart.exceptions;

public class phoneNumberNotMatchException extends Exception {
	
	private String userCredential;
	public phoneNumberNotMatchException(String userCredential) {
		this.userCredential = userCredential;
	}
	public String getuserCredential() {
		return userCredential;
	}
}
