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
	            + "current_semester int NOT NULL,"
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
	
	public int getSemester(String id) {
		
		int sem=0;
		try {
			
			String sql="Select current_semester from CRS.student where studentId=?";
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			sem=resultSet.getInt(1);
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sem;
	}
	
	public void setSemester(String id,int sem) {
	
		try {
			String sql="Update CRS.student set current_semester=? where studentId=?";
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setInt(1, sem);
			statement.setString(2, id);
			statement.executeUpdate();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
