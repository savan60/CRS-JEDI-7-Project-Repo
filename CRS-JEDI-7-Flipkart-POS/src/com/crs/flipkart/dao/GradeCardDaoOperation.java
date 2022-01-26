/**
 * 
 */
package com.crs.flipkart.dao;

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
}
