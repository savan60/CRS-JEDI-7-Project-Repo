/**
 * 
 */
package com.crs.flipkart.dao;
import java.util.*;


import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.constant.*;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.exceptions.GradeCardNotCreatedException;


import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * @author SAVAN
 *
 */
public class GradeCardDaoOperation implements GradeCardDaoInterface{
	
	private static Logger logger = Logger.getLogger(GradeCardDaoOperation.class);
	
	/**
	 * 
	 * 
	 * Initializes table in database for storing grade card information
	 */
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
	
	/**
	 * 
	 * Generate all grade cards for semester sem
	 * @param sem 
	 * 
	 */
	public void gradeCardGen(int sem) {
		logger.info("gradeCardGen method started");

		Connection connection = DBUtils.getConnection();
		PreparedStatement statement = null;
		RegisteredCourseDaoOperation reg=new RegisteredCourseDaoOperation();
		
	
		
		try {
			HashMap<String,Float> map= reg.generateGradeCardBySem(sem);
			
			for(Map.Entry<String, Float> e : map.entrySet()) {
				
				String id=sem+ e.getKey();
				
				String grade=SQLQueriesConstant.gradeCardGenQuery;
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
	
	
	/**
	 *
	 * Returns registered course objects for student with studentId in semester sem
	 * @param studentId
	 * @param sem
	 * 
	 * @return ArrayList<RegisteredCourse>
	 */
	public ArrayList<RegisteredCourse> fetchRegisteredSemesterCoursesForStudents(String studentId, int semester) {
		/*
		 * Method to fetch semester courses allocated to a student
		 * @param studentId, semester
		 * @return List of RegisteredCourses
		 * 
		 */
		logger.info("fetchRegisteredSemesterCoursesForStudents method started");

		Connection conn = DBUtils.getConnection();
		ArrayList<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();
		String sql = SQLQueriesConstant.fetchRegisteredCourseFromStudentId;
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, studentId);
			statement.setInt(2, semester);
			
			ResultSet rs = statement.executeQuery();
			logger.info("Creating gradeCard for student "+studentId+" semester "+semester);
			while (rs.next()) {
				courses.add(new RegisteredCourse(rs.getString("registeredCourseId"), rs.getString("courseId"), rs.getString("studentId"),
						rs.getFloat("grade"), rs.getInt("semester")));
			}
		}

		catch (SQLException e) {
			logger.error("SQL Error : "+e.getMessage());
			e.printStackTrace();
		}
		
		return courses;
	}
	
	/**
	 * 
	 * Returns gradeCard for student with studentId in semester sem
	 * @param studentId
	 * @param semester
	 * @return GradeCard
	 * @throws GradeCardNotCreatedException
	 */
	public GradeCard fetchGradeCard(String studentId, int semester) throws GradeCardNotCreatedException{
		
		/*
		 * Method to fetch GradeCard of student for a given semester
		 * If gradeCard is not created yet, then a dummy gradeCard would be returned with NOTFOUND as its gradeCardId
		 * @param studentId, semester
		 * @return GradeCard
		 */
		logger.info("fetchGradeCard method started");

		Connection conn = DBUtils.getConnection();
		GradeCard gradeCard = new GradeCard();
		
		gradeCard.setGradeCardId("NOTFOUND");
		String sql = SQLQueriesConstant.fetchGradeCardUsingStudentId;
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, studentId);
			statement.setInt(2, semester);
			logger.info("Fetching gradeCard for Student "+studentId+" semester "+semester);
			
			ResultSet rs = statement.executeQuery();
		
			while (rs.next()) {
				gradeCard.setGradeCardId(rs.getString("gradeCardId"));
				gradeCard.setSemester(semester);
				gradeCard.setStudentID(studentId);
				gradeCard.setGrade(rs.getFloat("grade"));
			}
			
			return gradeCard;
		}
		catch (Exception ex) {
			throw new GradeCardNotCreatedException(studentId, semester);
		}
		finally {
			try {
				conn.close();
			}catch(SQLException ex){
				logger.error("SQL ERROR "+ ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}

