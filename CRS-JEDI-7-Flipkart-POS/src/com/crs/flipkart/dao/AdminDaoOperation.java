/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.Map;

import com.crs.flipkart.bean.Professor;
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
	
	public static void createTable() {
		 String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.admin ("
		         + "AdminId VARCHAR(20) NOT NULL,"
		         + "doj DATE NULL,"
		         + "PRIMARY KEY (AdminId))";
		 DBUtils.createTable(SCHEMA);
	}
	 
	public boolean addProfessorToDB(Professor professor) {
		String userInsertQuery = SqlUtils.INSERT_USER;
		String profInsertQuery = SqlUtils.INSERT_PROFESSOR;
		
		int userQueryRes=0, profQueryRes=0;
		
		// Adding info to User DB
		
		try {
			stmt = (PreparedStatement) conn.prepareStatement(userInsertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(1,professor.getUserId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.setString(2,professor.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(3,professor.getPhoneNumber());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(4,professor.getAddress());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(5,professor.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(6,UserType.Professor.name());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			userQueryRes = stmt.executeUpdate();
//			System.out.print(userQueryRes+" ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Adding info to Professor DB
		
		try {
			stmt = (PreparedStatement) conn.prepareStatement(profInsertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt.setString(1,professor.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt.setString(2,professor.getDepartment());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt.setString(4,professor.getPosition());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			profQueryRes = stmt.executeUpdate();
//			System.out.println(profQueryRes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(userQueryRes==1 && profQueryRes==1) return true;
		return false;
	}
	 
	 public void approveStudents(int count){
		 HashSet<String> set=new HashSet<>();
		 
		 try {
			 StudentDaoOperation student=new StudentDaoOperation();
		 
			 if(count==1) {
				 
				 String sql = "UPDATE CRS.student SET isApproved=? WHERE isApproved = false";
				 
				 stmt = (PreparedStatement) conn.prepareStatement(sql);
				 stmt.setBoolean(1,true);
					
				 int resultSet = stmt.executeUpdate();
			 }
			 
			 else {				 
				 
				 set.addAll(student.getStudentListForApproval());
				
				 for(String str:set) {
					 System.out.println("You want to approve Student with id: "+str);
					 System.out.println("Press 1 for yes and 2 for no");
					 int ch=sc.nextInt();
					 String sql = "UPDATE CRS.student SET isApproved=? WHERE studentId="+str;
					 
					 stmt = (PreparedStatement) conn.prepareStatement(sql);
					 if(ch==1)
						 stmt.setBoolean(1,true);
					 
					 else stmt.setBoolean(1,false);
					 
					 int resultSet = stmt.executeUpdate();
				 }

			 }
				
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
	 }
}
