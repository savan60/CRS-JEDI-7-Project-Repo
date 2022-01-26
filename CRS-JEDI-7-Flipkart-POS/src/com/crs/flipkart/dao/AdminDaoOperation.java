/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.util.*;
import java.util.Map;

/**
 * @author SAVAN
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {
	
	Connection conn = DBConnection.mysqlConnection;
	private PreparedStatement stmt = null;
	Scanner sc=new Scanner(System.in);
	
	
	 public static void createTable() {
		 String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.admin ("
		         + "AdminId VARCHAR(20) NOT NULL,"
		         + "doj DATE NULL,"
		         + "PRIMARY KEY (AdminId))";
         DBConnection.createTable(SCHEMA);
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
