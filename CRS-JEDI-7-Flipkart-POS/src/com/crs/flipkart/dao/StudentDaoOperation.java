/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author SAVAN
 *
 */
public class StudentDaoOperation implements StudentDaoInterface{
	
	Connection conn = DBConnection.mysqlConnection;
	private PreparedStatement stmt = null;
	
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.student ("
		     	+ "studentId VARCHAR(20) NOT NULL,"
	            + "name VARCHAR(45) NOT NULL,"
		     	+ "isApproved boolean DEFAULT false,"
	            + "PRIMARY KEY (studentId))";
		DBConnection.createTable(SCHEMA);
	}
	
	public HashSet<String> getStudentListForApproval() {
		

		HashSet<String> students=new HashSet<>();
		try {
			
			
			String sql = "Select studentId from CRS.student where isApproved = ?";
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setBoolean(1,false);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				
				
				String id=resultSet.getString(1);
				students.add(id);
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return students;
	}
	
}
