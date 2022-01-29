/**
 * 
 */
package com.crs.flipkart.exceptions;

import org.apache.log4j.Logger;

/**
 * @author ADARSH
 *
 */
public class CourseNotDeletedException extends Exception {
	private static Logger logger =Logger.getLogger(CourseNotAddedException.class);
	private String courseId; 

	public CourseNotDeletedException(String courseId) {
		logger.info("Instance creation of CourseNotDeletedException");
		this.courseId = courseId;
	}


	public String getCourseId() {
		return courseId;
	}


	public String getMessage() {
		return "Course with courseId: " + courseId + " can't be deleted.";
	}
}
