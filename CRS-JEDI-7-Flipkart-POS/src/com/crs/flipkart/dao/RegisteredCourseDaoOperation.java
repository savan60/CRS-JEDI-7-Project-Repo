/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crs.flipkart.bean.RegisteredCourse;

/**
 * @author SAVAN
 *
 */
public class RegisteredCourseDaoOperation implements RegisteredCourseDaoInterface {

	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT exists CRS.registeredCourse(" + "registeredCourseId varchar(20) NOT NULL,"
				+ "courseId varchar(20) NOT NULL," + "studentId varchar(20) NOT NULL," + "grade float NOT NULL,"
				+ "semester float NOT NULL," + "PRIMARY KEY (registeredCourseId),"
				+ "FOREIGN KEY (courseId) REFERENCES course(courseId),"
				+ "FOREIGN KEY (studentId) REFERENCES student(studentId))";
		DBConnection.createTable(SCHEMA);
	}

	public static void printEnrolledStudentInThatCourse(String courseId) {
		Connection conn = DBConnection.mysqlConnection;

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select studentId from CRS.registeredcourse where courseId =" + courseId;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString("studentId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void updateGrade(String courseId, String studentId, float newGrade) {
		Connection conn = DBConnection.mysqlConnection;

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "update CRS.registeredcourse set grade=" + newGrade + " where courseId=" + courseId + " and studentId="
					+ studentId;
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}
