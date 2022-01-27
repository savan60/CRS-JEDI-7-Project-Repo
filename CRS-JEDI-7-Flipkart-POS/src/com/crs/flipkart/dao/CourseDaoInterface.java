/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public interface CourseDaoInterface {
	public  void viewCourses(int sem);
	public  void updateProfessorId(String ProfessorId, String CourseId);
	public  void addCourToDB(String CourseId,String CourseName,Float CourseDur,Float CourseCre);
	/*
	 * using this function admin can add a course to the list of courses available.
	 * */
	public  void delCourse(String CourseId);
	/*
	 * using this method admin can delete course from the list of courses available.
	 * */
}
