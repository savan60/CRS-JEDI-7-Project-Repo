package com.crs.flipkart.exceptions;

/**
 * 
 * 
 * 
 * @author nikhil.kumar3
 * 
 * Raised when No student is there for approval
 *
 */
public class NoStudentForApprovalException extends Exception {
	
	int count;
	
	public NoStudentForApprovalException(int count) {
		this.count=count;
	}
	
	public String getMsg(){
	return "No Student left for approval";
	}
}
