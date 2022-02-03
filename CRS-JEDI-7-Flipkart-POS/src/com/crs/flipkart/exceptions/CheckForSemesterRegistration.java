/**
 * 
 */
package com.crs.flipkart.exceptions;

import com.crs.flipkart.constant.COLORCONSTANT;

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
		System.out.println(COLORCONSTANT.TEXT_RED);
		return "Already registred for sem : " + sem;
	}
	
}
