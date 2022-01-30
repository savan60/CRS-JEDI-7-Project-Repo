/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

/**
 * @author parth
 *
 */
public interface ProfessorInterface {
	
	public HashMap<String, ArrayList<Pair<String, String>>> viewEnrolledStudents(String professorId);
	public void addGrade(String professorId,float grade, String studentId, String courseId);
	public void addCourse(String professorId, String courseId);
	public void viewCourse(String professorId);

}
