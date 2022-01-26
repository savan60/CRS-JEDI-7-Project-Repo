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

	public HashMap<String, Course> getCourses();	
	public void setCourses(HashMap<String, Course> courses);
	public boolean addCourse(String id,String subj,float duration ,float credits);
	public boolean removeCourse(String id);
	
}
