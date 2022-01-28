package com.crs.flipkart.exceptions;

public class PasswordNotMatchException extends Exception {
	private String userCredential;
	public PasswordNotMatchException(String userCredential) {
		this.userCredential = userCredential;
	}
	public String getuserCredential() {
		return userCredential;
	}
}
