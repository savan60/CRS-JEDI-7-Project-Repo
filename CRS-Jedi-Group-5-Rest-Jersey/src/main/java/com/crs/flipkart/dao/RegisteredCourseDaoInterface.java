/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.*;

import com.crs.flipkart.exceptions.AddCourseLimitExceed;
import com.crs.flipkart.exceptions.CourseNotEndrolledByStudent;
import com.crs.flipkart.exceptions.GradeCardByCourseIdFoundEmpty;
import com.crs.flipkart.exceptions.GradeCardBySemFoundEmpty;
import com.crs.flipkart.utils.Pair;

/**
 * @author SAVAN
 *
 */
public interface RegisteredCourseDaoInterface {
	public ArrayList<Pair> printEnrolledStudentInThatCourse(String courseId) throws GradeCardByCourseIdFoundEmpty;
	public boolean updateGrade(String courseId, String studentId, float newGrade) ;
	public HashMap<String,Float> generateGradeCardBySem(int sem) throws GradeCardBySemFoundEmpty;
	public boolean addCourse(String courseId, String studentId,int sem) throws AddCourseLimitExceed;
	public boolean dropCourse(String courseId, String studentId) throws CourseNotEndrolledByStudent;
	public  void printRegisteredCourses(String studentId, int sem) ;
}
