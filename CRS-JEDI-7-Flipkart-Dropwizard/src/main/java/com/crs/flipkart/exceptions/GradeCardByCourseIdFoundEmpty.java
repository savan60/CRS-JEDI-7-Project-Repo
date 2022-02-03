package com.crs.flipkart.exceptions;
/**
 * 
 * 
 * 
 * @author nikhil.kumar3
 *
 * Raised when Grade card for the course is not present
 */

public class GradeCardByCourseIdFoundEmpty extends Exception{
	private String courseId;
	   public GradeCardByCourseIdFoundEmpty(String courseId)
	   {
	      this.courseId = courseId;
	   } 
	   public String getCourseId()
	   {
	      return courseId;
	   }
}
