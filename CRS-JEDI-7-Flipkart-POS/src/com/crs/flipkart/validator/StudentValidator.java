/**
 * 
 */
package com.crs.flipkart.validator;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;

/**
 * @author ADARSH
 *
 */
public class StudentValidator {
	public static boolean isValidCourseCode(String courseId, ArrayList<Course>courses) {

		for (Course course : courses) {
			if (courseId.equals(course.getCourseId())) {
				return true;
			}
		}
		return false;
	}
}
