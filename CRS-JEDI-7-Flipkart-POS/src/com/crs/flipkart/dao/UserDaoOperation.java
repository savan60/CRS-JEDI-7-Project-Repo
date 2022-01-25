/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class UserDaoOperation implements UserDaoInterface{
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.user("
		         + "userId VARCHAR(20) NOT NULL,"
		         + "email VARCHAR(20) NOT NULL," +"phoneNumber VARCHAR(10) NOT NULL," +"address VARCHAR(40),"
		         + "password VARCHAR(20) NOT NULL," +"userType enum('Admin', 'Student','Professor'),"
		         + "PRIMARY KEY (userId))";
		DBConnection.createTable(SCHEMA);
	}
}
