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
public interface CourseInterface {

	public ArrayList<Course> getCourses();	
	public void setCourses(ArrayList<Course> courses);
	
}
