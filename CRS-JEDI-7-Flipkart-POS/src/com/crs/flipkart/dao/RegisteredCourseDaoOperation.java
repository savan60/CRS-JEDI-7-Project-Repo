/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.util.*;

import com.crs.flipkart.bean.RegisteredCourse;

/**
 * @author SAVAN
 *
 */
public class RegisteredCourseDaoOperation implements RegisteredCourseDaoInterface {

	Connection conn = DBConnection.mysqlConnection;
	private PreparedStatement stmt = null;
//	Statement stmt=null;
	
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT exists CRS.registeredCourse(" + "registeredCourseId varchar(20) NOT NULL,"
				+ "courseId varchar(20) NULL," + "studentId varchar(20) NULL," + "grade float NOT NULL,"
				+ "semester float NOT NULL," + "PRIMARY KEY (registeredCourseId))";
		DBConnection.createTable(SCHEMA);
	}

	public void printEnrolledStudentInThatCourse(String courseId) {

		try {
			stmt = (PreparedStatement) conn.createStatement();
			String query = "select studentId from CRS.registeredcourse where courseId =" + courseId;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString("studentId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void updateGrade(String courseId, String studentId, float newGrade) {

		try {
			stmt = (PreparedStatement) conn.createStatement();
			String query = "update CRS.registeredcourse set grade=" + newGrade + " where courseId=" + courseId + " and studentId="
					+ studentId;
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public HashMap<String,Float> generateGradeCardBySem(int sem) {
		

		HashMap<String,Float> grade=new HashMap<>();
		try {
			
			
			String sql = "Select studentId, sum(grade)/4 as SGPA from CRS.registeredCourse where semester = ? group by studentId";
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setInt(1,sem);
			
			ResultSet resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				
				
				String id=resultSet.getString(1);
				Float sgpa=resultSet.getFloat(2);
				
				grade.put(id,sgpa);
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return grade;
	}
}
