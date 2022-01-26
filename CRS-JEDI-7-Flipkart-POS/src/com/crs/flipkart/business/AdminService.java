/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Date;

import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.dao.*;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author Shruti
 *
 */
public class AdminService implements AdminInterface {
	
	UserService user=new UserService();
	AdminDaoOperation admin=new AdminDaoOperation();
	
	public boolean addProfessor(String id, String email, Long num, String add, String pass) {
		return user.addUser(id, email, num, add, pass, UserType.Professor);
	}
	
	public void verifyStudent() {
		
	}
		
//	public void addCourse() {
//		
//	}
//	
//	public void removeCourse() {
//		
//	}
	
	public void approveAllStudents() {
		admin.approveStudents(1);
	}
	public void approveStudentsOneByOne() {
		admin.approveStudents(0);
	}
	
	public void genReportCard(int sem) {
		GradeCardDaoOperation grade=new GradeCardDaoOperation();
		
		grade.gradeCardGen(sem);
	}
	
	public void updateAddDropTime() {
		
	}

	

}