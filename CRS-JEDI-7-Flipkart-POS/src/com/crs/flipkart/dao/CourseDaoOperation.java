/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
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
	public static ArrayList<Course> courses = new ArrayList<>();

	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.course (" + "courseId VARCHAR(20) NOT NULL,"
				+ "professorId VARCHAR(20) NOT NULL," + "name VARCHAR(20) NOT NULL," + "duration float NOT NULL,"
				+ "credits float NOT NULL," + "FOREIGN KEY (professorId) REFERENCES professor(professorId),"
				+ "PRIMARY KEY (courseId))";
		DBConnection.createTable(SCHEMA);
	}

	public static void app() {
		Connection conn = DBConnection.mysqlConnection;

		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from CRS.course");

			while (rs.next()) {
				courses.add(new Course(rs.getString("courseId"), rs.getString("professorId"), rs.getString("name"),
						rs.getFloat("duration"), rs.getFloat("credits")));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> fetchCourseIdFromProfessorId(String ProfessorId) {
		Connection conn = DBConnection.mysqlConnection;
		ArrayList<String> listOfCourseId = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select courseId from CRS.course where professorId =" + ProfessorId;

			ResultSet rs = stmt.executeQuery(query);
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

	public static void updateProfessorId(String ProfessorId, String CourseId) {
		Connection conn = DBConnection.mysqlConnection;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "update CRS.course set professorId = " + ProfessorId + " where courseId = " + CourseId;
			stmt.executeUpdate(query);
			
			query="select * from CRS.course";
			ResultSet rs=stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(
						"CourseId: " + rs.getString("courseId") + " ProfessorId:" + rs.getString("professorId"));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		DBConnection.setup();
//		updateProfessorId("100", "100");
//	}
}
