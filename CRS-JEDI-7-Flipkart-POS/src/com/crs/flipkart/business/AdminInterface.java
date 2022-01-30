package com.crs.flipkart.business;

import com.crs.flipkart.bean.Professor;

/**
 * @author Shruti
 *
 */
public interface AdminInterface {

	public boolean addProfessor(Professor prof) ;
	
	public void approveAllStudents();
	public void approveStudentsOneByOne();
	
	public void genReportCard(int sem) ;
	
	public void updateAddDropTime();
}