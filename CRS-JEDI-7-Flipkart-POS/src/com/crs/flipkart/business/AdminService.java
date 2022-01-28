/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Date;
import java.util.Scanner;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.dao.*;

/**
 * @author Shruti
 *
 */
public class AdminService implements AdminInterface {
	
	UserService user=new UserService();
	AdminDaoOperation admin=new AdminDaoOperation();
	
	public boolean addProfessor() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the user id:");
		String professorId=sc.next();
		
		System.out.println("Enter the emailid:");
		String email=sc.next();
		
		System.out.println("Enter the phoneNumber:");
		String phoneNumber=sc.next();
		
		System.out.println("Enter the address:");
		String address=sc.next();
		
		System.out.println("Enter the password:");
		String password=sc.next();
		
		System.out.println("Enter Department:");
		String department=sc.next();
		
		System.out.println("Enter Position:");
		String position=sc.next();
		
		Professor newProfessor = new Professor(professorId,email,phoneNumber,address,password,department,new Date(),position);
		
		AdminDaoInterface admin = new AdminDaoOperation();
		
		if(admin.addProfessorToDB(newProfessor))
			return true;
		return false;
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