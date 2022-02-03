/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Statement;
import java.util.ArrayList;



import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.SqlUtils;
import com.crs.flipkart.constant.SQLQueriesConstant;


import com.crs.flipkart.exceptions.StudentNotFound;

/**
 * @author SAVAN
 *
 */
public class StudentDaoOperation implements StudentDaoInterface{
	
	private PreparedStatement statement = null;
	Connection connection = DBUtils.getConnection();
	private static Logger logger = Logger.getLogger(StudentDaoOperation.class);
	// create student table if it not exists
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.student ("
		     	+ "studentId VARCHAR(20) NOT NULL,"
	            + "name VARCHAR(45) NOT NULL,"
	            + "isApproved BOOLEAN NOT NULL,"
	            + "phoneNumber VARCHAR(10) NOT NULL," 
	            + "address VARCHAR(40),"
	            + "current_semester int default 1,"
	            + "PRIMARY KEY (studentId))";
		DBUtils.createTable(SCHEMA);
	}
	
	/**
	 * 
	 * Returns a list of students for approval, which stores student id and their approval status
	 * 
	 * @return HashSet<String> 
	 */
	public HashSet<String> getStudentListForApproval() {
		logger.info("getStudentListForApproval method started");


		HashSet<String> students=new HashSet<>();
		try {
			String sql = "Select studentId from CRS.student where isApproved = ?";
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setBoolean(1,false);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {		
				String id=resultSet.getString(1);
				students.add(id);
			}	
		}
		catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
		}
		
		return students;
		
		}
		
	/**
 	 * add student into student table 
	 * @param student
	 * 
	 * 
	 */

	public boolean addStudent(Student student) {
		logger.info("addStudent method started");

		try {
			Connection conn = DBUtils.getConnection();
			
			// adds student to user table
			UserDaoOperation userDaoOperation = new  UserDaoOperation();
			userDaoOperation.addUser(student);
			
			
			// adds student to student table
			statement = null;
			String sql = "INSERT INTO `CRS`.`student` (`studentId`,`phoneNumber`,`address`, `name`, `isApproved`) VALUES (?, ? , ? , ?, ?);";
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1,student.getStudentId());
			statement.setString(2, student.getPhoneNumber());
			statement.setString(3,student.getAddress());
			statement.setString(4,student.getName());
			statement.setBoolean(5,false);
			int res=statement.executeUpdate();
			System.out.println("res is "+res);
			if(res!=0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
		}
		return false;
	}
	
	
	/**
	 * Get semester for student with studentId id
	 * 
	 * @param id
	 * @return int
	 * 
	 * 
	 */
	public int getSemester(String id) throws StudentNotFound{
		logger.info("getSemester method started");

		int sem=1;
		try {
			Connection connection = DBUtils.getConnection();

			String sql="Select current_semester from CRS.student where studentId=?";
			statement = (PreparedStatement) connection.prepareStatement(sql);
			logger.debug("student id is "+id);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				sem=resultSet.getInt(1);
			}
			return sem;
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		throw new StudentNotFound(id);
	}
	
	
	/**
	 * 
	 * Set semester for the student with studentId id
	 * 
	 * @param id
	 * @param sem
	 * @throws StudentNotFound
	 * 
	 */
	public void setSemester(String id,int sem) throws StudentNotFound{
		logger.info("setSemester method started");

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
		
		throw new StudentNotFound(id);
	}
	
	
	/**
	 * 
	 * Return ids of all the students as an arrayList
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getAllStudentIds(){
		logger.info("getAllStudentIds method started");

		ArrayList<String> studentIds = new ArrayList<String>();
		
		try {
			Connection conn = DBUtils.getConnection();
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
	
	/**
	 * 
	 * Returns name of the student with studentId
	 * 
	 * @return String
	 * @param StudentId
	 * 
	 */
	public String getStudentNameFromId(String studentId) {
		logger.info("getStudentNameFromId method started");

		logger.info("Getting student name for StudentID:"+studentId);
		Connection conn = DBUtils.getConnection();
		String sql = SQLQueriesConstant.fetchStudentNameFromId;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				return rs.getString("name");
			}
		}catch(SQLException e){
			logger.error("Error Message : "+e.getMessage());
		}
		return null;
	}
}
