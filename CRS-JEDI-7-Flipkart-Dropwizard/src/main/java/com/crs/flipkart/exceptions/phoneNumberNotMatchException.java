package com.crs.flipkart.exceptions;
/**
 * 
 * 
 * 
 * 
 * @author nikhil.kumar3
 *
 *
 * Raised when phone number doesn't match the phone number in database
 */
public class phoneNumberNotMatchException extends Exception {
	
	private String userCredential;
	public phoneNumberNotMatchException(String userCredential) {
		this.userCredential = userCredential;
	}
	public String getuserCredential() {
		return userCredential;
	}
}
