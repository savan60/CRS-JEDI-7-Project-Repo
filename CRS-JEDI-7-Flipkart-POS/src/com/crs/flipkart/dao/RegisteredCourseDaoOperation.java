/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.constant.SQLQueriesConstant;
import com.crs.flipkart.exceptions.AddCourseLimitExceed;
import com.crs.flipkart.exceptions.CourseNotEndrolledByStudent;
import com.crs.flipkart.exceptions.GradeCardByCourseIdFoundEmpty;
import com.crs.flipkart.exceptions.GradeCardBySemFoundEmpty;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.Pair;
import com.crs.flipkart.utils.Utils;

/**
 * @author SAVAN
 *
 */
public class RegisteredCourseDaoOperation implements RegisteredCourseDaoInterface {

	static Connection conn = DBUtils.getConnection();
	static PreparedStatement stmt = null;
	private static Logger logger=Logger.getLogger(RegisteredCourseDaoOperation.class);
	
	
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT exists CRS.registeredCourse(" + "registeredCourseId varchar(50) NOT NULL,"
				+ "courseId varchar(20) NULL," + "studentId varchar(20) NULL," + "grade float NOT NULL,"
				+ "semester INTEGER NOT NULL," + "PRIMARY KEY (registeredCourseId))";
		DBUtils.createTable(SCHEMA);
	}
	
	public ArrayList<Pair> printEnrolledStudentInThatCourse(String courseId) throws GradeCardByCourseIdFoundEmpty{
		
		logger.info("printEnrolledStudentInThatCourse started");
		ArrayList<Pair> listOfStudentsInThatCourse=new ArrayList<>();
		try {
			conn=DBUtils.getConnection();
			PreparedStatement stmt1 ;
			String query=SQLQueriesConstant.printEnrolledStudentInThatCourseQuery;
			stmt1=(PreparedStatement) conn.prepareStatement(query);
			stmt1.setString(1, courseId);
			ResultSet rs = stmt1.executeQuery();
			int count =0;
			while (rs.next()) {
				count+=1;
				listOfStudentsInThatCourse.add(new Pair(rs.getString("name"), rs.getString("studentId")));
			}
			if(count==0) {
				throw new GradeCardByCourseIdFoundEmpty(courseId);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listOfStudentsInThatCourse;
		
	}

	public void updateGrade(String courseId, String studentId, float newGrade) {
		
		conn=DBUtils.getConnection();
//		Statement stmt1;
		
		try {
			String query = SQLQueriesConstant.updateGradeQuery;
			
			stmt = (PreparedStatement) conn.prepareStatement(query);
			stmt.setFloat(1,newGrade);
			stmt.setString(2,courseId);
			stmt.setString(3,studentId);
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public HashMap<String,Float> generateGradeCardBySem(int sem) throws GradeCardBySemFoundEmpty{
		HashMap<String,Float> grade=new HashMap<>();
		try {
			
			
			String sql = SQLQueriesConstant.generateGradeCardBySemQuery;
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setInt(1,sem);
			
			ResultSet resultSet = stmt.executeQuery();
			int count=0;
			while (resultSet.next()) {
				
				count++;
				String id=resultSet.getString(1);
				Float sgpa=resultSet.getFloat(2);
				
				grade.put(id,sgpa);
			}
			if(count==0) {
				throw new GradeCardBySemFoundEmpty(sem);
			}
			
		} catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
		}
		return grade;
	}
	
	
	public boolean dropCourse(String courseId, String studentId) throws CourseNotEndrolledByStudent
	{
		Connection conn = DBUtils.getConnection();

//		Statement stmt;
		try {
			String query = SQLQueriesConstant.dropCourseQuery;
			stmt = (PreparedStatement) conn.prepareStatement(query);
			stmt.setString(1, courseId);
			stmt.setString(2, studentId);
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public void printRegisteredCourses(String studentId, int sem) {
		Connection conn = DBUtils.getConnection();
		Statement stmt;

		try{
			stmt = conn.createStatement();
			String query = "select * from CRS.registeredCourse r inner join CRS.course c on c.courseId=r.courseId where r.studentId='"
					+ studentId + "' and r.semester=" + sem;
		
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				//Changes required:
				//need to pass this list of student in crsapplication and print there, no print statement should present outside crs application which is to be shown to user
				System.out.println("Course name:-->"+rs.getString("name")+"   Course Id:--> " + rs.getString("courseId") + "  Grades:--> " + rs.getFloat("grade"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addCourse(String courseId, String studentId,int sem) throws AddCourseLimitExceed
	{
		Connection conn = DBUtils.getConnection();

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
				throw new AddCourseLimitExceed();
			}
			else
			{
				String query = "INSERT INTO `CRS`.`registeredCourse` (`registeredCourseId`, `courseId`, `studentId`, `grade`, `semester`) VALUES (?, ?, ?, ?, ?)"; // change 3 -> used system generated id
				String id = Utils.generateId().toString();
				id = id.substring(0, Math.min(id.length(), 10));
				logger.debug(id);
				stmt1 = (PreparedStatement) conn.prepareStatement(query);
				stmt1.setString(1, id);
				stmt1.setString(2, courseId);
				stmt1.setString(3, studentId);
				stmt1.setFloat(4, 0);
				stmt1.setInt(5, sem);
				int response=stmt1.executeUpdate();
				//Changes required:
				//need to check number of courses endrolled by particluar student in particluar sem
				//here you are checking all the entries which is wrong
				query = "select count(*) from CRS.registeredCourse";
				ResultSet res = stmt.executeQuery(query);
				res.next();
				int count1 = res.getInt(1);
				count = count+1;
				//Changes required:
				//need to pass this list of student in crsapplication and print there, no print statement should present outside crs application which is to be shown to user
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




