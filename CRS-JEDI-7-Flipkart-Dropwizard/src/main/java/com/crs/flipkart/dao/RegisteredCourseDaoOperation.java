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
	
	/**
	 * 
	 * Create table to store registered course data
	 */
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT exists CRS.registeredCourse(" + "registeredCourseId varchar(50) NOT NULL,"
				+ "courseId varchar(20) NULL," + "studentId varchar(20) NULL," + "grade float NOT NULL,"
				+ "semester INTEGER NOT NULL," + "PRIMARY KEY (registeredCourseId))";
		DBUtils.createTable(SCHEMA);
	}
	
	
	/**
	 * Get details for students enrolled in course by courseId
	 * 
	 * @param courseId
	 * @throws GradeCardByCourseIdFoundEmpty
	 * @return ArrayList<Pair>
	 */
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

	
	/**
	 * 
	 * update grade of the student with studentId for the course with courseId as newGrade in database
	 * 
	 * @param courseId
	 * @param studentId
	 * @param newGrade
	 * 
	 */
	public boolean updateGrade(String courseId, String studentId, float newGrade) {
		
		
//		Statement stmt1;
		logger.info("updateGrade method started");

		try {
			PreparedStatement stmt1 = null;
			conn=DBUtils.getConnection();
			String query = SQLQueriesConstant.updateGradeQuery;
			System.out.println("Hello");
			stmt1 = (PreparedStatement) conn.prepareStatement(query);
			stmt1.setFloat(1,newGrade);
			stmt1.setString(2,courseId);
			stmt1.setString(3,studentId);
			int res=stmt1.executeUpdate();
			System.out.println(res);
			if(res==1)return true;

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}
	
	/**
	 * 
	 * Get grade cards for semester sem 
	 * 
	 * @param sem
	 * @return HashMap<String, Float>
	 * @throws GradeCardBySemFoundEmpty 
	 * 
	 */
	public HashMap<String,Float> generateGradeCardBySem(int sem) throws GradeCardBySemFoundEmpty{
		logger.info("generateGradeCardBySem method started");

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
	
	
	/**
	 * drop courseId from the courses assigned to student with studentId
	 * 
	 * @param studentId
	 * @param courseId
	 * @return boolean true if course dropped successfully, else false
	 */
	public boolean dropCourse(String studentId, String courseId)
	{
		logger.info("dropCourse method started");

		Connection conn = DBUtils.getConnection();

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query_check = "select count(*) from CRS.registeredCourse where studentId = '" + studentId +"' and courseId = '" + courseId + "'";
			ResultSet r = stmt.executeQuery(query_check);
			r.next();
			int ct = r.getInt(1);
			if (ct==0)
			{
				System.out.println("You did not enroll in this course");
				return false;
			}
			String query = "delete from CRS.registeredCourse where courseId = '" + courseId + "' and studentId = '" + studentId + "';";
			stmt.executeUpdate(query);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * Print registered course for student with studentId in semester sem
	 * 
	 * @param studentId
	 * @param sem
	 * 
	 * 
	 * 
	 */
	public String printRegisteredCourses(String studentId, int sem) {
		logger.info("printRegisteredCourses method started");

		Connection conn = DBUtils.getConnection();
		Statement stmt;

		try{
			stmt = conn.createStatement();
			String query = "select * from CRS.registeredCourse r inner join CRS.course c on c.courseId=r.courseId where r.studentId='"
					+ studentId + "' and r.semester=" + sem;
		
			ResultSet rs = stmt.executeQuery(query);
			String res = "";   
		       
	        while (rs.next()) {
	           
	            res += "Course name:-->"+rs.getString("name")+"   Course Id:--> " + rs.getString("courseId") + "  Grades:--> " + rs.getFloat("grade") + "\n";
	        }

	        return res;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Add course with courseId to the courses assigned to student with studentId
	 * 
	 * @param courseId
	 * @param studentId
	 * @param sem
	 * @throws AddCourseLimitExceed
	 * @return boolean true if course assigned successfully else false
	 */
	public boolean addCourse(String courseId, String studentId,int sem) throws AddCourseLimitExceed
	 {
		logger.info("addCourse method started");

		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt1;
		Statement stmt;
		try {
			//System.out.println("HELLO");
			stmt = conn.createStatement();
			String new_check = "select count(*) from CRS.registeredCourse where studentId = '" + studentId +"' and courseId = '" + courseId + "'";
			ResultSet r = stmt.executeQuery(new_check);
			r.next();
			int ct = r.getInt(1);
			//System.out.println("For sid = " + studentId + " and course = " + courseId + "ct is  " + ct);
			if (ct>=1)
			{
				
				System.out.println("You already added this course");
				return false;
			}
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




