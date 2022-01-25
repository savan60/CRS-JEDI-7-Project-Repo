/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class CourseDaoOperation implements CourseDaoInterface {
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.course (" + "courseId VARCHAR(20) NOT NULL,"
				+"professorId VARCHAR(20) NOT NULL,"
				+ "name VARCHAR(20) NOT NULL," + "duration float NOT NULL," + "credits float NOT NULL,"
				+ "FOREIGN KEY (professorId) REFERENCES professor(professorId)," + "PRIMARY KEY (courseId))";
		DBConnection.createTable(SCHEMA);
	}
}
