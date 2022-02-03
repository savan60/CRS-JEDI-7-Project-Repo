/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author SAVAN
 *
 *
 *
 * Raised when student is not enrolled in the course
 */
public class CourseNotEndrolledByStudent extends Exception {
	private String courseId;
	   public CourseNotEndrolledByStudent(String courseId)
	   {
	      this.courseId=courseId;
	   } 
	   public String getCourseId()
	   {
	      return courseId;
	   }
}
