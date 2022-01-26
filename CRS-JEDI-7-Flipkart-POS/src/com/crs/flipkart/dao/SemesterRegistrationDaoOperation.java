/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class SemesterRegistrationDaoOperation implements SemesterRegistrationDaoInterface{
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.semesterRegistration ("
	            + "semesterRegistrationId VARCHAR(20) NOT NULL,"
		     	+ "studentId VARCHAR(20) NULL,"
	            + "semester INT NOT NULL,"
	            + "date  DATE NOT NULL,"
	            + "PRIMARY KEY (semesterRegistrationId))";
		DBConnection.createTable(SCHEMA);
	}
	
}
