/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Student;

/**
 * @author SAVAN
 *
 */
public interface StudentDaoInterface {

	
	public void addStudent(Student student); 
	public ArrayList<String> getAllStudentIds();
	
}
