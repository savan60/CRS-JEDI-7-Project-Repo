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
	
	public boolean addProfessor(Professor newProfessor) {
		
		logger.info("add professor function started");
		try {
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