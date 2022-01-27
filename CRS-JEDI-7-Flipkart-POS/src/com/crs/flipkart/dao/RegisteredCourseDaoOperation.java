/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.util.*;

import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.utils.Utils;

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
			String query = "select studentId from CRS.registeredCourse where courseId ='" + courseId + "';";
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
			String query = "update CRS.registeredCourse set grade=" + newGrade + " where courseId='" + courseId + "' and studentId='"
					+ studentId + "';";
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
	
	
	public boolean dropCourse(String courseId, String studentId)
	{
		Connection conn = DBConnection.mysqlConnection;

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "delete from CRS.registeredCourse where courseId = '" + courseId + "' and studentId = '" + studentId + "';";
			stmt.executeUpdate(query);
			return true;

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
			String query = "select * from CRS.registeredCourse r inner join CRS.course c on c.courseId=r.courseId where r.studentId='"
					+ studentId + "' and r.semester=" + sem;
		
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("Course name:-->"+rs.getString("name")+"   Course Id:--> " + rs.getString("courseId") + "  Grades:--> " + rs.getFloat("grade"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public boolean addCourse(String courseId, String studentId,int sem)
	{
		Connection conn = DBConnection.mysqlConnection;

		PreparedStatement stmt1;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String check = "select count(*) from CRS.registeredCourse where studentId = '" + studentId +"'";
			ResultSet rs = stmt.executeQuery(check); // change 1 -> added check for course count
			rs.next();
			int count = rs.getInt(1);
			if (count>=6)
			{
				System.out.println("You cannot add more than 6 courses"); 
			}
			
			else
			{
				String query = "INSERT INTO `CRS`.`registeredCourse` (`registeredCourseId`, `courseId`, `studentId`, `grade`, `semester`) VALUES (?, ?, ?, ?, ?)"; // change 3 -> used system generated id
				String id = Utils.generateId().toString();
				id = id.substring(0, Math.min(id.length(), 10));
				System.out.println(id);
				stmt1 = (PreparedStatement) conn.prepareStatement(query);
				stmt1.setString(1, id);
				stmt1.setString(2, courseId);
				stmt1.setString(3, studentId);
				stmt1.setFloat(4, 0);
				stmt1.setInt(5, sem);
				stmt1.execute();
				
				query = "select count(*) from CRS.registeredCourse";
				ResultSet res = stmt.executeQuery(query);
				res.next();
				int count1 = res.getInt(1);
				count = count+1;
				System.out.println("You have added " + count + " courses.");
				if(count <= 6) {
					return true;
				}
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}


