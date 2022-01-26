/**
 * 
 */
package com.crs.flipkart.dao;
import java.util.ArrayList;


import com.crs.flipkart.bean.RegisteredCourse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author SAVAN
 *
 */
public class GradeCardDaoOperation implements GradeCardDaoInterface {
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.gradecard ("
		         + "gradeCardId VARCHAR(20) NOT NULL,"
		         + "studentId VARCHAR(20),"
		         + "semester INT NOT NULL,"
		         + "grade FLOAT,"
		         + "PRIMARY KEY (gradeCardId))";
		DBConnection.createTable(SCHEMA);
	}
	
	public static ArrayList<RegisteredCourse> fetchRegisteredSemesterCoursesForStudents(String studentId, float semester) {
		Connection conn = DBConnection.mysqlConnection;
		ArrayList<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from CRS.registeredCourse where studentId ="+studentId+" AND semester="+semester;
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				courses.add(new RegisteredCourse(rs.getString("registeredCourseId"), rs.getString("courseId"), rs.getString("studentId"),
						rs.getFloat("grade"), rs.getInt("semester")));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return courses;
	}
}
