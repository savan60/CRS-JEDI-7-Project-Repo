/**
 * 
 */
package com.crs.flipkart.exceptions;


/**
 * @author parth
 *
 *
 * 
 * Exception raised when Student already registered for the semester
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
