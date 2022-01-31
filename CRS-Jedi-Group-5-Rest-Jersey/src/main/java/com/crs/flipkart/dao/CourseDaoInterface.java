/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.exceptions.CourseNotAddedException;
import com.crs.flipkart.exceptions.CourseNotDeletedException;

import com.crs.flipkart.bean.Course;
import java.util.ArrayList;
/**
 * @author SAVAN
 *
 */
public interface CourseDaoInterface {
	public  void viewCourses(int sem);
	public  boolean updateProfessorId(String ProfessorId, String CourseId);
	public  void addCourToDB(String CourseId,String CourseName,Float CourseDur,Float CourseCre) throws CourseNotAddedException;
	/*
	 * using this function admin can add a course to the list of courses available.
	 * */
	public  void delCourse(String CourseId) throws CourseNotDeletedException;
	/*
	 * using this method admin can delete course from the list of courses available.
	 * */
	
	
	public Course getCourseFromId(String courseId);
	public ArrayList<String> fetchCourseIdFromProfessorId(String ProfessorId);
	
	/*
	 * To get course name from course ID
	 * @param courseId
	 * 
	 */
}
