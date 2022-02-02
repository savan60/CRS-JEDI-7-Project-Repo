/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author parth
 *
 */
public class CheckForSemesterRegistration extends Exception{
	private int sem;
	public CheckForSemesterRegistration(int sem) {
		this.sem = sem;
	}
	
	public String getMessage() {
		return "Already registred for sem : " + sem;
	}
	
}
