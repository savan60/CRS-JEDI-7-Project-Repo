/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.*;

/**
 * @author SAVAN
 *
 */
public interface RegisteredCourseDaoInterface {
	public void printEnrolledStudentInThatCourse(String courseId);
	public void updateGrade(String courseId, String studentId, float newGrade);
	public HashMap<String,Float> generateGradeCardBySem(int sem);
	public boolean addCourse(String courseId, String studentId,int sem);
	public boolean dropCourse(String courseId, String studentId);
	public  void printRegisteredCourses(String studentId, int sem) ;
}
