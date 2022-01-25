/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.professor (" + "professorId VARCHAR(20) NOT NULL,"
				+ "dept VARCHAR(10) NOT NULL," + "doj DATE NOT NULL," + "pos VARCHAR(10)," + "PRIMARY KEY (professorId))";
		DBConnection.createTable(SCHEMA);
	}
}
