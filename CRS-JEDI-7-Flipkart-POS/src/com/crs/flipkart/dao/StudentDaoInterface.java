/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exceptions.StudentNotFound;

/**
 * @author SAVAN
 *
 */
public interface StudentDaoInterface {

	public void setSemester(String id,int sem) throws StudentNotFound;
	public int getSemester(String id) throws StudentNotFound;
	public void addStudent(Student student) throws StudentNotFound; 
	public ArrayList<String> getAllStudentIds();
	public String getStudentNameFromId(String studentId);
	
}
