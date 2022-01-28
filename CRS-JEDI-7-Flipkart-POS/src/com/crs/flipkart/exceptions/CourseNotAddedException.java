/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author ADARSH
 *
 */
public class CourseNotAddedException extends Exception{
	private String courseId; 

	public CourseNotAddedException(String courseId) {
		this.courseId = courseId;
	}


	public String getCourseId() {
		return courseId;
	}


	public String getMessage() {
		return "Course with courseCode: " + courseId + " can't be added.";
	}
}
