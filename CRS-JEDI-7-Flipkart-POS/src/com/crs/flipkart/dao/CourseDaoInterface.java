/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.exceptions.CourseNotAddedException;
import com.crs.flipkart.exceptions.CourseNotDeletedException;

/**
 * @author SAVAN
 *
 */
public interface CourseDaoInterface {
	public  void viewCourses(int sem);
	public  void updateProfessorId(String ProfessorId, String CourseId);
	public  void addCourToDB(String CourseId,String CourseName,Float CourseDur,Float CourseCre) throws CourseNotAddedException;
	/*
	 * using this function admin can add a course to the list of courses available.
	 * */
	public  void delCourse(String CourseId) throws CourseNotDeletedException;
	/*
	 * using this method admin can delete course from the list of courses available.
	 * */
}
