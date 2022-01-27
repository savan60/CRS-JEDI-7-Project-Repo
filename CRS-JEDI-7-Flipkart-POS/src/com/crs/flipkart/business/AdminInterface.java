package com.crs.flipkart.business;

/**
 * @author Shruti
 *
 */
public interface AdminInterface {

	public boolean addProfessor() ;
	
	public void verifyStudent() ;
	
//	public void addCourse() ;
//	
//	public void removeCourse() ;
	public void approveAllStudents();
	public void approveStudentsOneByOne();
	
	public void genReportCard(int sem) ;
	
	public void updateAddDropTime();
}