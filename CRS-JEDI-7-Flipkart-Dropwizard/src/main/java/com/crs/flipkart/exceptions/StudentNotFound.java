/**
 * 
 */
package com.crs.flipkart.exceptions;


/**
 * @author DELL NOTEBOOK
 *
 *
 * Raised when student is not found in database
 */
public class StudentNotFound extends Exception{

	private String studentId;
	
	public StudentNotFound(String id)
	   {
	      this.studentId = id;
	   } 
	public String getId()
	   {
	      return studentId;
	   }
}
