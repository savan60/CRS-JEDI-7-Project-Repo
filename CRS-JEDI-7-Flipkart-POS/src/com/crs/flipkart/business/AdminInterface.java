/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author SAVAN
 *
 */
public interface AdminInterface {

	public boolean addProfessor(String userId, String email, String phoneNumber, String address, String password, String department, String position) ;
	
	public void verifyStudent() ;
		
	public void addCourse() ;
	
	public void removeCourse() ;
	
	
	public void getReportCard() ;
	
	public void updateAddDropTime();
}
