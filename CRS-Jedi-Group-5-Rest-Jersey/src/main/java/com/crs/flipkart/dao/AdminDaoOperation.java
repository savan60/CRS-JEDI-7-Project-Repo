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
		
		
		int userQueryRes=0, profQueryRes=0;
		
		// Adding info to User DB
		
		try {
			UserDaoOperation userDaoOperation = new  UserDaoOperation();
			userDaoOperation.addUser(professor);
			PreparedStatement stmt1 = null;
			System.out.print(userQueryRes+" ");
			conn=DBUtils.getConnection();
			String sql="insert into crs.professor(professorId,phoneNumber,address,dept,doj,pos) values (?,?,?,?,?,?)";
			stmt1 = (PreparedStatement) conn.prepareStatement(sql);
			stmt1.setString(1,professor.getUserId());
			stmt1.setString(2, professor.getPhoneNumber());
			stmt1.setString(3,professor.getAddress());
			stmt1.setString(4,professor.getDepartment());
			stmt1.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stmt1.setString(6,professor.getPosition());
			profQueryRes = stmt1.executeUpdate();
			System.out.println(profQueryRes);
			
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
	  
	public boolean approveStudents(String id) throws NoStudentForApprovalException{
		 
		 HashSet<String> set=new HashSet<>();
		 int resultSet=0;
		 try {
			 StudentDaoOperation student=new StudentDaoOperation();
		 
			 if(id.equals("0")) {
				 
				 String sql = "UPDATE CRS.student SET isApproved=? WHERE isApproved = ?";
				 
				 stmt = (PreparedStatement) conn.prepareStatement(sql);
				 stmt.setBoolean(1,true);
				 stmt.setBoolean(2,false);
				 resultSet = stmt.executeUpdate();
				 logger.info("All Students are approved!");
				 if (resultSet!=0)
					 return true;
				 else return false;
			 }
			 
			 else {				 
				 
					 String sql = "UPDATE CRS.student SET isApproved=? WHERE studentId=?";
					 
					 stmt = (PreparedStatement) conn.prepareStatement(sql);
					 stmt.setString(2, id);
					 
					 
				     stmt.setBoolean(1,true);
					 logger.info("This student is approved!");
					
					 resultSet = stmt.executeUpdate();
					 if (resultSet!=0)
						 return true;
					 else return false;

			 }
				
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		 	
		 	throw new NoStudentForApprovalException(resultSet);
		 	
	 }
}
