/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.dao.*;
import com.crs.flipkart.exceptions.*;

/**
 * @author Shruti
 *
 */
public class AdminService implements AdminInterface {
	private static Logger logger=Logger.getLogger(AdminService.class);
	
	UserService user=new UserService();
	AdminDaoOperation admin=new AdminDaoOperation();
	
	public boolean addProfessor() {
		
		logger.info("add professor function started");
		try {
			
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
		}
		catch(UserAlreadyExistsException e) {
			logger.error("This user already exists: "+e.getEmail());
		}
		return false;
	}
	

	public void approveAllStudents() {
		logger.info("Approve all students function started");
		
		try {
			admin.approveStudents(1);
		}
		catch(NoStudentForApprovalException e) {
			logger.error(e.getMsg());
		}
		
	}
	public void approveStudentsOneByOne() {
		logger.info("Approve students One By One function started");
		
		try {
			admin.approveStudents(0);
		}
		catch(NoStudentForApprovalException e) {
			System.out.println(e.getMsg());
		}
	}
	
	public void genReportCard(int sem) {
		
		logger.info("Generate report card function started");
		
		GradeCardDaoOperation grade=new GradeCardDaoOperation();
		
		grade.gradeCardGen(sem);
	}
	
	public void updateAddDropTime() {
		
	}



	

}