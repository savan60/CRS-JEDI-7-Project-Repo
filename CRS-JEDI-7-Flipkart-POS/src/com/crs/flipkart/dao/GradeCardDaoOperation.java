/**
 * 
 */
package com.crs.flipkart.dao;
import java.util.ArrayList;


import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.GradeCard;
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
	
	public static ArrayList<RegisteredCourse> fetchRegisteredSemesterCoursesForStudents(String studentId, int semester) {
		Connection conn = DBConnection.mysqlConnection;
		ArrayList<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from CRS.registeredCourse where studentId ="+studentId+" AND semester="+semester+" AND isAllocated = true";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				courses.add(new RegisteredCourse(rs.getString("registeredCourseId"), rs.getString("courseId"), rs.getString("studentId"),
						rs.getFloat("grade"), rs.getInt("semester"), rs.getTimestamp("timestamp"), rs.getBoolean("isAllocated")));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return courses;
	}
	
	public static GradeCard fetchGradeCard(String studentId, int semester, String GradeCardId) {
		Connection conn = DBConnection.mysqlConnection;
		GradeCard gradeCard = new GradeCard();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from CRS.gradecard where studentId ="+studentId+" AND semester="+semester+" AND gradeCardId = "+GradeCardId;
			ResultSet rs = stmt.executeQuery(query);
			boolean found = false;
			while (rs.next()) {
				found = true;
				gradeCard.setGradeCardId(rs.getString("gradeCardId"));
				gradeCard.setSemester(semester);
				gradeCard.setStudentID(studentId);
				gradeCard.setGrade(rs.getFloat("grade"));
			}
			
			if(!found) {
				gradeCard.setGradeCardId("NOTFOUND");
			}
			return gradeCard;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
