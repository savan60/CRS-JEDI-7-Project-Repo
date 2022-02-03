/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author SAVAN
 *
 *
 * 
 * Exception raised when registered courses are 6
 */

public class AddCourseLimitExceed extends Exception {
	private int course;
	   public AddCourseLimitExceed()
	   {
	      this.course=6;
	   } 
	   public int getCourse()
	   {
	      return course;
	   }
	   
}
