/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.Map;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.exceptions.*;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.SqlUtils;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author SAVAN
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {
	
	Connection conn = DBUtils.getConnection();
	private PreparedStatement stmt = null;
	Scanner sc=new Scanner(System.in);
	private static Logger logger=Logger.getLogger(AdminDaoOperation.class);
	
	public static void createTable() {
		 String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.admin ("
		         + "AdminId VARCHAR(20) NOT NULL,"
		         + "doj DATE NULL,"
		         + "phoneNumber VARCHAR(10) NOT NULL," 
		         + "address VARCHAR(40),"
		         + "PRIMARY KEY (AdminId))";
		 DBUtils.createTable(SCHEMA);
	}
	 
	public boolean addProfessorToDB(Professor professor) throws UserAlreadyExistsException{
		String profInsertQuery = SqlUtils.INSERT_PROFESSOR;
		
		int userQueryRes=0, profQueryRes=0;
		
		// Adding info to User DB
		
		try {
			UserDaoOperation userDaoOperation = new  UserDaoOperation();
			userDaoOperation.addUser(professor);
			
//			System.out.print(userQueryRes+" ");

			stmt = (PreparedStatement) conn.prepareStatement(profInsertQuery);
			stmt.setString(1,professor.getUserId());
			stmt.setString(2, professor.getPhoneNumber());
			stmt.setString(3,professor.getAddress());
			stmt.setString(4,professor.getDepartment());
			stmt.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stmt.setString(6,professor.getPosition());
			profQueryRes = stmt.executeUpdate();
//			System.out.println(profQueryRes);
			
			if(profQueryRes==1) {
				logger.info("Professor added to DB");
				return true;
			}
			
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new UserAlreadyExistsException(professor.getEmail());
		
	}
	 
	 public void approveStudents(int count) throws NoStudentForApprovalException{
		 
		 HashSet<String> set=new HashSet<>();
		 int resultSet=0;
		 try {
			 StudentDaoOperation student=new StudentDaoOperation();
		 
			 if(count==1) {
				 
				 String sql = "UPDATE CRS.student SET isApproved=? WHERE isApproved = ?";
				 
				 stmt = (PreparedStatement) conn.prepareStatement(sql);
				 stmt.setBoolean(1,true);
				 
				 resultSet = stmt.executeUpdate();
				 logger.info("All Students are approved!");
			 }
			 
			 else {				 
				 
				 set.addAll(student.getStudentListForApproval());
				
				 for(String str:set) {
					 System.out.println("You want to approve Student with id: "+str);
					 System.out.println("Press 1 for yes and 2 for no");
					 int ch=sc.nextInt();
					 String sql = "UPDATE CRS.student SET isApproved=? WHERE studentId=?";
					 
					 stmt = (PreparedStatement) conn.prepareStatement(sql);
					 stmt.setString(2, str);
					 
					 if(ch==1) {
						 stmt.setBoolean(1,true);
						 logger.info("This student is approved!");
					 }
						 
					 
					 else {
						 
						 stmt.setBoolean(1,false);
						 logger.info("This student is not approved!");
					 }
					 
					 resultSet = stmt.executeUpdate();
					 
<<<<<<< HEAD
					 resultSet = stmt.executeUpdate();
					 logger.info("Student is approved!");
=======
>>>>>>> 3ab313f9f7988a532ba55c3daa229c4528a7a192
				 }

			 }
				
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		 	throw new NoStudentForApprovalException(resultSet);
	 }
}
