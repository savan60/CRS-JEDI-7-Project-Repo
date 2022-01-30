package com.crs.flipkart.business;

import com.crs.flipkart.bean.Professor;

/**
 * @author Shruti
 *
 */
public interface AdminInterface {

<<<<<<< HEAD
	public boolean addProfessor(String email,String phoneNumber,String address,String password,String department,String position);
=======
	public boolean addProfessor() ;
	
>>>>>>> 3ab313f9f7988a532ba55c3daa229c4528a7a192
	public void approveAllStudents();
	public void approveStudentsOneByOne();
	public void genReportCard(int sem) ;
	public void updateAddDropTime();
}