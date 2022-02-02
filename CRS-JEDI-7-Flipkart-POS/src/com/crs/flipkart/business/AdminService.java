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
import com.crs.flipkart.utils.Utils;

/**
 * @author Shruti
 *
 */
public class AdminService implements AdminInterface {
	private static Logger logger=Logger.getLogger(AdminService.class);
	
	UserInterface user=new UserService();
	AdminDaoInterface admin=new AdminDaoOperation();
	
	public int addProfessor(String email, String phoneNumber, String address, String password, String department, String position) {
		
		logger.info("add professor function started");
		try {
			String professorId = Utils.generateUniqueId().substring(0,3) + Utils.generateUniqueId().substring(10,13);
			
			Date date = new Date();
			
			Professor newProfessor = new Professor(professorId,email,phoneNumber,address,password,department,date,position);
			
			AdminDaoInterface admin = new AdminDaoOperation();
			if(admin.addProfessorToDB(newProfessor))
				return 1;
		}
		catch(UserAlreadyExistsException e) {
			logger.error("This user already exists: "+e.getEmail());
			return 2;
		}
		return 0;
	}
	

	public boolean approveAllStudents() {
		logger.info("Approve all students function started");
		
		try {
			admin.approveStudents(1);
			return true;
		}
		catch(NoStudentForApprovalException e) {
			logger.error(e.getMsg());
			return false;
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