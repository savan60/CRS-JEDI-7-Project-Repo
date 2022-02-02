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

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.constant.SQLQueriesConstant;
import com.crs.flipkart.exceptions.CourseNotAddedException;
import com.crs.flipkart.exceptions.CourseNotDeletedException;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author SAVAN
 *
 */
public class CourseDaoOperation implements CourseDaoInterface {
	
	private static Logger logger =Logger.getLogger(CourseDaoOperation.class);
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.course (" 
							+ "courseId VARCHAR(20) NOT NULL,"
							+ "professorId VARCHAR(20) NULL," 
							+ "name VARCHAR(20) NOT NULL," 
							+ "duration float NOT NULL,"
							+ "semester int NOT NULL," 
							+ "credits float NOT NULL," 
							+ "PRIMARY KEY (courseId))";
		DBUtils.createTable(SCHEMA);
	}

	public static ArrayList<Course>  getAllCourses() {
		logger.info("Fetching all the courses");
		ArrayList<Course> courses = new ArrayList<>();

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql="select * from CRS.course";
			stmt =conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				courses.add(new Course(rs.getString("courseId"), rs.getString("professorId"), rs.getString("name"),
						rs.getFloat("duration"), rs.getFloat("credits")));
			}
			logger.info("Fetched all the courses");
			return courses;
		}

		catch (SQLException e) {
			logger.error("Error message: "+e.getMessage());
		}
		
		return null;
	}

	public ArrayList<String> fetchCourseIdFromProfessorId(String ProfessorId) {
		logger.info("Fetching all courses that the professor "+ProfessorId+" teaches");
		Connection conn = DBUtils.getConnection();
		ArrayList<String> listOfCourseId = new ArrayList<>();
		PreparedStatement stmt = null;
		try {
			stmt =conn.prepareStatement(SQLQueriesConstant.fetchCourseIdFromProfessorId);
			stmt.setString(1,ProfessorId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listOfCourseId.add(rs.getString("courseId"));
			}
			logger.info("Fetched all courses");
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error message: "+e.getMessage());
		}
		return listOfCourseId;
	}

	public void viewCourses(int sem) {
		logger.info("Fetching all the courses in the sem ="+sem);
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		try {

			stmt =conn.prepareStatement(SQLQueriesConstant.viewCourcesQuery);
			stmt.setInt(1,sem);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"Course Id :" + rs.getString("courseId") + " ProfessorId: " + rs.getString("professorId")
								+ " Course Name: " + rs.getString("name") + " Credits: " + rs.getInt("credits"));
			}
			logger.info("Fetched all courses");
		}

		catch (SQLException e) {
			logger.error("Error message: "+e.getMessage());
		}
	}

	public void updateProfessorId(String ProfessorId, String CourseId) {
		logger.info("Allocating professor"+ProfessorId+"to that course"+CourseId);
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		try {
	
			stmt =conn.prepareStatement(SQLQueriesConstant.updateProfessorIdQuery);
			stmt.setString(1, ProfessorId);
			stmt.setString(2, CourseId);
			stmt.executeUpdate();
			ResultSet rs = stmt.executeQuery(SQLQueriesConstant.selectAllCoursesQuery);
			while (rs.next()) {
				System.out.println(
						"CourseId: " + rs.getString("courseId") + " ProfessorId:" + rs.getString("professorId"));
			}
			logger.info("Professor "+ProfessorId+" is allocated to the course+ "+CourseId );
		}

		catch (SQLException e) {
			logger.error("Error message: "+e.getMessage());
		}
		
	}
	
	public void addCourToDB(String CourseId,String CourseName,Float CourseDur,Float CourseCre) throws CourseNotAddedException {
		logger.info("Adding course "+CourseName+" to catalog");
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement stmt = null;
			//String sql = "INSERT INTO `CRS`.`course` (`courseId`, `name`, `duration`, `credits`, `semester`) VALUES (?, ?, ?, ?, ?);";
			stmt = (PreparedStatement) conn.prepareStatement(SQLQueriesConstant.addCoursesToDb);
			stmt.setString(1,CourseId);
			stmt.setString(2,CourseName);
			stmt.setFloat(3, CourseDur);
			stmt.setFloat(4, CourseCre);
			stmt.setInt(5, 1);
			stmt.execute();
			logger.info("Course is added to catalog");
			return;
		}

		catch (SQLException e) {
			logger.error("Error message: "+e.getMessage());
		}
		throw new CourseNotAddedException(CourseId);
	}
	public void delCourse(String CourseId) throws CourseNotDeletedException {
		logger.info("Deleting course "+CourseId+" from catalog");
		Connection conn = DBUtils.getConnection();
		
		try {
			PreparedStatement stmt = null;
			stmt = (PreparedStatement) conn.prepareStatement(SQLQueriesConstant.deleteCourseFromDb);
			//String query = "delete from CRS.course where courseId= ?";
			stmt.setString(1,CourseId);			
			stmt.execute();	
			logger.info("Course is deleted from the catalog");
			return;
		}

		catch (SQLException e) {
			logger.error("Error message: "+e.getMessage());
		}
		throw new CourseNotDeletedException(CourseId);
	}
	
	public Course getCourseFromId(String courseId) {
		logger.info("Getting course for CourseID:"+courseId);
		Connection conn = DBUtils.getConnection();
		String sql = SQLQueriesConstant.fetchCourseFromId;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, courseId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				return new Course(rs.getString("courseId"),rs.getString("professorId"),rs.getString("name"),rs.getFloat("duration"),rs.getFloat("credits"));
			}		
		}catch(SQLException e){
			logger.error("Error Message : "+e.getMessage());
		}
		return null;
	}
	
	
}
