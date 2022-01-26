/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public interface RegisteredCourseDaoInterface {
	public boolean addCourse(String courseId, String studentId,int sem);
	public boolean dropCourse(String courseId, String studentId);
	public  void printRegisteredCourses(String studentId, int sem) ;
}
