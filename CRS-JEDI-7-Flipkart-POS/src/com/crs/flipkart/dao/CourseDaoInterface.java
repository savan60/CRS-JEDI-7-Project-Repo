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
	public  void delCourse(String CourseId);
}
