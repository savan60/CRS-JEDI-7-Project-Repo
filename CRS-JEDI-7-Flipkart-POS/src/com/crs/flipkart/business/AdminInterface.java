package com.crs.flipkart.business;

import com.crs.flipkart.bean.Professor;

/**
 * @author Shruti
 *
 */
public interface AdminInterface {
	public int addProfessor(String email,String phoneNumber,String address,String password,String department,String position);
	public boolean approveAllStudents();
	public void approveStudentsOneByOne();
	public void genReportCard(int sem) ;
	public void updateAddDropTime();
}