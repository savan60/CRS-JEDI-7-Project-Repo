/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class RegisteredCourseDaoOperation implements RegisteredCourseDaoInterface{
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT exists CRS.registeredCourse("+
				"registeredCourseId varchar(20) NOT NULL,"+
				"courseId varchar(20) NOT NULL,"+
				"studentId varchar(20) NOT NULL,"+
				"grade float NOT NULL,"+
				"semester float NOT NULL,"+
				"PRIMARY KEY (registeredCourseId),"+
				"FOREIGN KEY (courseId) REFERENCES course(courseId),"+
				"FOREIGN KEY (studentId) REFERENCES student(studentId))";
		DBConnection.createTable(SCHEMA);
	}
}
