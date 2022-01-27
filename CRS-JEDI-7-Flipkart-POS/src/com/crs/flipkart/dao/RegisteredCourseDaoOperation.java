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
		String SCHEMA = "CREATE TABLE IF NOT exists CRS.registeredCourse(" + "registeredCourseId varchar(50) NOT NULL,"
				+ "courseId varchar(20) NULL," + "studentId varchar(20) NULL," + "grade float NOT NULL,"
				+ "semester INTEGER NOT NULL," + "PRIMARY KEY (registeredCourseId))";
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
	
	public boolean addCourse(String courseId, String studentId,int sem)
	{
		Connection conn = DBConnection.mysqlConnection;

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "insert into CRS.registeredcourse(registeredCourseId, courseId, studentId, grade, semester) values(" + courseId+studentId +", "+ courseId + "," + studentId + ", 0,"+sem+");";
			int res=stmt.executeUpdate(query);
			if(res==1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean dropCourse(String courseId, String studentId)
	{
		Connection conn = DBConnection.mysqlConnection;

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "delete from CRS.registeredcourse where courseId = " + courseId + " and studentId = " + studentId + ";";
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public void printRegisteredCourses(String studentId, int sem) {
		Connection conn = DBConnection.mysqlConnection;
		Statement stmt;

		try{
			stmt = conn.createStatement();
			String query = "select * from CRS.registeredcourse r inner join CRS.course c on c.courseId=r.courseId where r.studentId="
					+ studentId + " and r.semester=" + sem;
		
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("Course name:-->"+rs.getString("name")+"   Course Id:--> " + rs.getString("courseId") + "  Grades:--> " + rs.getFloat("grade"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}
