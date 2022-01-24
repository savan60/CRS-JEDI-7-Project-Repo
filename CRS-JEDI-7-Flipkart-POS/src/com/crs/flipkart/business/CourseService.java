/**
 * 
 */
package com.crs.flipkart.business;
import java.util.ArrayList;
import java.util.HashMap;

import com.crs.flipkart.bean.Course;

/**
 * @author parth
 *
 */
public class CourseService implements CourseInterface {

	private HashMap<String ,Course> courses = new HashMap<String, Course>();

	public CourseService() {
		
		courses.put("100", new Course("100", "Maths", 90 , 3));
		courses.put("102", new Course("102", "Compiler", 90 , 3));
		courses.put("103", new Course("103", "Data Structure", 90 , 3));
		courses.put("104", new Course("104", "Programming", 90 , 3));
		courses.put("105", new Course("105", "Electronics", 90 , 3));
		
	}

	/**
	 * @return the courses
	 */
	public HashMap<String, Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	
	public void setCourses(HashMap<String, Course> courses) {
		this.courses = courses;
	}
	
	
	
}
