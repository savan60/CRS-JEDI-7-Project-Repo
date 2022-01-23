/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.RegisteredCourse;

/**
 * @author ADARSH
 *
 */
public class RegisteredCourseService {
	public static RegisteredCourse[] reg= {new RegisteredCourse("100", "100","100", 0, 1),new RegisteredCourse("101", "100","101", 0, 1),new RegisteredCourse("102", "200","302", 0, 1)};
	public void submitGrade() {
		//course id, student id => pk => find 1 row => setgrade
	}
}
