/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author parth
 *
 */
public interface ProfessorInterface {
	
	public void viewEnrolledStudents(String professorId);
	public void addGrade(String professorId,float grade, String studentId, String courseId);
	public void addCourse(String professorId, String courseId);
	public void viewCourse(String professorId);

}
