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
	public HashMap<String,Float> generateGradeCardBySem(int sem);
	public boolean addCourse(String courseId, String studentId,int sem);
	public boolean dropCourse(String courseId, String studentId);
	public  void printRegisteredCourses(String studentId, int sem) ;
}
