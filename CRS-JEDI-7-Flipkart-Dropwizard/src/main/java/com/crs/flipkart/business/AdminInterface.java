package com.crs.flipkart.business;

import com.crs.flipkart.bean.Professor;

/**
 * @author Shruti
 *
 */
public interface AdminInterface {
	public boolean addProfessor(String email,String phoneNumber,String address,String password,String department,String position);
	public boolean approveAllStudents(String id);
	public void genReportCard(int sem) ;
}