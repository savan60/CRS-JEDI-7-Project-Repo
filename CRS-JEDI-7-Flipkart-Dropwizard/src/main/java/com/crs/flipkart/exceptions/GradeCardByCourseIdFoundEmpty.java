package com.crs.flipkart.exceptions;

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
