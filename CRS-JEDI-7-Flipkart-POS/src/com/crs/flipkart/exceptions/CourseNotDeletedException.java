/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author ADARSH
 *
 */
public class CourseNotDeletedException extends Exception {
	private String courseId; 

	public CourseNotDeletedException(String courseId) {
		this.courseId = courseId;
	}


	public String getCourseId() {
		return courseId;
	}


	public String getMessage() {
		return "Course with courseId: " + courseId + " can't be deleted.";
	}
}
