/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.utils.SqlUtils;

/**
 * @author SAVAN
 *
 */
public class StudentDaoOperation implements StudentDaoInterface{
	
	private PreparedStatement statement = null;
	Connection connection = DBConnection.mysqlConnection;
	
	//add student sem
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.student ("
		     	+ "studentId VARCHAR(20) NOT NULL,"
	            + "name VARCHAR(45) NOT NULL,"
	            + "isApproved BOOLEAN NOT NULL,"
	            + "PRIMARY KEY (studentId))";
		DBConnection.createTable(SCHEMA);
	}
	
	public void addStudent(Student student) {

		try {
			DBConnection.setup();
			Connection conn = DBConnection.mysqlConnection;
			
			// adds student to user table
			UserDaoOperation userDaoOperation = new  UserDaoOperation();
			userDaoOperation.addUser(student);
			
			
			// adds student to student table
			statement = null;
			String sql = "INSERT INTO `CRS`.`student` (`studentId`, `name`, `isApproved`) VALUES (?, ?, ?);";
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1,student.getStudentId());
			statement.setString(2,student.getName());
			statement.setBoolean(3,false);
			statement.execute();
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	
	public ArrayList<String> getAllStudentIds(){
		ArrayList<String> studentIds = new ArrayList<String>();
		
		try {
			DBConnection.setup();
			Connection conn = DBConnection.mysqlConnection;
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select studentId from CRS.student");

			while (rs.next()) {
				studentIds.add(rs.getString("studentId"));
			}
			
			return studentIds;
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
