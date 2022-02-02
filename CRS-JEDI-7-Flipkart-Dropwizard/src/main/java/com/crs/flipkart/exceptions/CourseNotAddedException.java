/**
 * 
 */
package com.crs.flipkart.exceptions;

import org.apache.log4j.Logger;

/**
 * @author ADARSH
 *
 */
public class CourseNotAddedException extends Exception{
	private static Logger logger =Logger.getLogger(CourseNotAddedException.class);
	private String courseId; 

	public CourseNotAddedException(String courseId) {
		logger.info("Instance creation of CourseNotAddedException");
		this.courseId = courseId;
	}


	public String getCourseId() {
		return courseId;
	}


	public String getMessage() {
		return "Course with courseCode: " + courseId + " can't be added.";
	}
}
