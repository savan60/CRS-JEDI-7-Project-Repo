package com.crs.flipkart.exceptions;

public class NoStudentForApprovalException extends Exception {
	
	int count;
	
	public NoStudentForApprovalException(int count) {
		this.count=count;
	}
	
	public String getMsg(){
	return "No Student left for approval";
	}
}
