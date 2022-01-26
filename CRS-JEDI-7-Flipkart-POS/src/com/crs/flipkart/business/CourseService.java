/**
 * 
 */
package com.crs.flipkart.business;
import java.util.ArrayList;
import java.util.HashMap;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.CourseDaoOperation;

/**
 * @author parth
 *
 */
public class CourseService implements CourseInterface {
	
	private ArrayList<Course> courses = CourseDaoOperation.courses;



	/**
	 * @return the courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	
	
	
	
}
