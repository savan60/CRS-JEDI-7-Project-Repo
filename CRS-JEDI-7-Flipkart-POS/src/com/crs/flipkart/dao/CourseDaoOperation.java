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
import java.util.HashMap;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;

/**
 * @author SAVAN
 *
 */
public class CourseDaoOperation implements CourseDaoInterface {
	
	
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.course (" + "courseId VARCHAR(20) NOT NULL,"
				+ "professorId VARCHAR(20) NULL," + "name VARCHAR(20) NOT NULL," + "duration float NOT NULL,"
				+ "semester int NOT NULL," + "credits float NOT NULL," + "PRIMARY KEY (courseId))";
		DBConnection.createTable(SCHEMA);
	}

	public static ArrayList<Course>  getAllCourses() {
		
		ArrayList<Course> courses = new ArrayList<>();

		Connection conn = DBConnection.mysqlConnection;
		PreparedStatement stmt = null;
		try {
			String sql="select * from CRS.course";
			stmt =conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				courses.add(new Course(rs.getString("courseId"), rs.getString("professorId"), rs.getString("name"),
						rs.getFloat("duration"), rs.getFloat("credits")));
			}
			
			return courses;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static ArrayList<String> fetchCourseIdFromProfessorId(String ProfessorId) {
		Connection conn = DBConnection.mysqlConnection;
		ArrayList<String> listOfCourseId = new ArrayList<>();
		PreparedStatement stmt = null;
		try {
			String sql = "select courseId from CRS.course where professorId =?";
			stmt =conn.prepareStatement(sql);
			stmt.setString(1,ProfessorId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listOfCourseId.add(rs.getString("courseId"));
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfCourseId;
	}

	public static void viewCourses(int sem) {
		Connection conn = DBConnection.mysqlConnection;
		PreparedStatement stmt = null;
		try {
			String sql= "select * from CRS.course where semester =?";
			stmt =conn.prepareStatement(sql);
			stmt.setString(1,sem);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(
						"Course Id :" + rs.getString("courseId") + " ProfessorId: " + rs.getString("professorId")
								+ " Course Name: " + rs.getString("name") + " Credits: " + rs.getInt("credits"));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateProfessorId(String ProfessorId, String CourseId) {
		Connection conn = DBConnection.mysqlConnection;
		Statement stmt;
		try {
	
			String sql = "update CRS.course set professorId = ? where courseId=?";
			stmt =conn.prepareStatement(sql);
			stmt.executeUpdate();

			query = "select * from CRS.course";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(
						"CourseId: " + rs.getString("courseId") + " ProfessorId:" + rs.getString("professorId"));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addCourToDB(String CourseId,String CourseName,Float CourseDur,Float CourseCre) {
	
		Connection conn = DBConnection.mysqlConnection;
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO `CRS`.`course` (`courseId`, `name`, `duration`, `credits`, `semester`) VALUES (?, ?, ?, ?, ?);";
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1,CourseId);
			stmt.setString(2,CourseName);
			stmt.setFloat(3, CourseDur);
			stmt.setFloat(4, CourseCre);
			stmt.setInt(5, 1);
			stmt.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void delCourse(String CourseId) {
		Connection conn = DBConnection.mysqlConnection;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "delete from CRS.course where courseId="+CourseId;
			stmt.executeUpdate(query);
			
			query="select * from CRS.course";
			ResultSet rs=stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(
						"CourseId: " + rs.getString("courseId") + " coursename:" + rs.getString("name"));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		DBConnection.setup();
//		viewCourses(2);
//	}
}
