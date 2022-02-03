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
	private static volatile AdminService instance = null;

	 /**
	 * 
	 * 
	 * create a instance of AdminSevice
	 *
	 * @return instance of the AdminService
	 * 
	 */
	public static AdminService getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminService.class){
				instance = new AdminService();
			}
		}
		return instance;
	}
	
	 /**
		 * Adds a professor to database
		 * 
		 * 
		 * @param email
		 * @param phoneNumber
		 * @param address
		 * @param password
		 * @param department
		 * @param position
		 * 
		 * @return boolean false if professor already exists, true if addition is successful
		 * 
		 */
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
	
	/**
	 * Approves all students at once
	 * 
	 */
	public boolean approveAllStudents(String id) {
		logger.info("Approve all students function started");
		
		try {
			if (admin.approveStudents(id))
				return true;
			else return false;
		}
		catch(NoStudentForApprovalException e) {
			logger.error(e.getMsg());
		}
		return false;
		
	}
	


	/**
	 * Generates report card fro all the students in a given semester
	 * 
	 * @param sem , semester to generate report card for
	 * 
	 * @return void
	 * 
	 */
	public void genReportCard(int sem) {
		logger.info("Generate report card function started");		
		
		GradeCardDaoOperation grade=new GradeCardDaoOperation();
		
		grade.gradeCardGen(sem);
	}
	

}