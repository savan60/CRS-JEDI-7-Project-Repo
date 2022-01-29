/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author DELL NOTEBOOK
 *
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
