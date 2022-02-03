package com.crs.flipkart.exceptions;

/**
 * 
 * 
 * 
 * 
 * @author nikhil.kumar3
 * 
 * 
 * Raised when Password doesn't match with the valid password
 *
 */
public class PasswordNotMatchException extends Exception {
	private String userCredential;
	public PasswordNotMatchException(String userCredential) {
		this.userCredential = userCredential;
	}
	public String getuserCredential() {
		return userCredential;
	}
}
