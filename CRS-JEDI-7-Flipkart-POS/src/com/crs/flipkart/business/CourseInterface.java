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

//	public ArrayList<Course> getCourses();	
//	/*
//	 * returns the list of courses
//	 * */
//	public void setCourses(ArrayList<Course> courses);
	public boolean addCourse(String id,String subj,float duration ,float credits);
	/*
	 * adds course to coursedb
	 * */
	public boolean deleteCourse(String id);
	/*
	 * deletes course from coursedb
	 * */
	
}
