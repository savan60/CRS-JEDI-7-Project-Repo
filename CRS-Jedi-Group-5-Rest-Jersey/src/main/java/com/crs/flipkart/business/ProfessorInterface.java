/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.crs.flipkart.utils.Pair;



/**
 * @author parth
 *
 */
public interface ProfessorInterface {
	
	public HashMap<String, ArrayList<Pair>> viewEnrolledStudents(String professorId);
	public boolean addGrade(String professorId,float grade, String studentId, String courseId);
	public boolean addCourse(String professorId, String courseId);
	public ArrayList<String> viewCourse(String professorId);

}
