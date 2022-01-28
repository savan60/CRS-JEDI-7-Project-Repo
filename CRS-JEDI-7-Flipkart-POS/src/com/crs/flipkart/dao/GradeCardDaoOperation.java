/**
 * 
 */
package com.crs.flipkart.dao;
import java.util.*;


import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.constant.*;
import com.crs.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author SAVAN
 *
 */
public class GradeCardDaoOperation implements GradeCardDaoInterface {
	
	public static void createTable() {
		/*
		 * Method to create GradeCard table in Database
		 * 
		 */
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.gradecard ("
		         + "gradeCardId VARCHAR(20) NOT NULL,"
		         + "studentId VARCHAR(20),"
		         + "semester INT NOT NULL,"
		         + "grade FLOAT,"
		         + "PRIMARY KEY (gradeCardId))";
		DBUtils.createTable(SCHEMA);
	}
	
	public void gradeCardGen(int sem) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement statement = null;
		RegisteredCourseDaoOperation reg=new RegisteredCourseDaoOperation();
		try {
			HashMap<String,Float> map= reg.generateGradeCardBySem(sem);
			
			for(Map.Entry<String, Float> e : map.entrySet()) {
				
				String id=sem+""+ e.getKey();
				System.out.println("");
//				String grade="INSERT INTO CRS.gradecard("
//						+id +"," 
//						+ e.getKey()+ "," 
//						+ sem+"," 
//						+ e.getValue() +")";
				String grade="insert into CRS.gradecard(gradeCardId, studentId, semester, grade) values (?, ?, ?, ?)";
				statement=connection.prepareStatement(grade);
				statement.setString(1, id);
				statement.setString(2, e.getKey());
				statement.setInt(3, sem);
				statement.setFloat(4, e.getValue());
				
				int result=statement.executeUpdate();
				
				System.out.println(result);
				}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static ArrayList<RegisteredCourse> fetchRegisteredSemesterCoursesForStudents(String studentId, int semester) {
		/*
		 * Method to fetch semester courses allocated to a student
		 * @param studentId, semester
		 * @return List of RegisteredCourses
		 * 
		 */
		Connection conn = DBUtils.getConnection();
		ArrayList<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();
		String sql = SQLQueriesConstant.fetchRegisteredCourseFromStudentId;
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, studentId);
			statement.setInt(2, semester);
			
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				courses.add(new RegisteredCourse(rs.getString("registeredCourseId"), rs.getString("courseId"), rs.getString("studentId"),
						rs.getFloat("grade"), rs.getInt("semester"), rs.getTimestamp("timestamp")));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return courses;
	}
	
	public static GradeCard fetchGradeCard(String studentId, int semester) {
		
		/*
		 * Method to fetch GradeCard of student for a given semester
		 * If gradeCard is not created yet, then a dummy gradeCard would be returned with NOTFOUND as its gradeCardId
		 * @param studentId, semester
		 * @return GradeCard
		 */
		
		Connection conn = DBUtils.getConnection();
		GradeCard gradeCard = new GradeCard();
		
		gradeCard.setGradeCardId("NOTFOUND");
		String sql = SQLQueriesConstant.fetchGradeCardUsingStudentId;
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, studentId);
			statement.setInt(2, semester);
			
			ResultSet rs = statement.executeQuery();
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

    //this is dummy gradeCard, in case no gradeCard is found, gradeID here will be NOTFOUND
    return gradeCard;
	}
}

