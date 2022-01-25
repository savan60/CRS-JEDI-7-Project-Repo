/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author SAVAN
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {
	 
	 public static void createTable() {
		 String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.admin ("
		         + "AdminId VARCHAR(20) NOT NULL,"
		         + "doj DATE NULL,"
		         + "PRIMARY KEY (AdminId))";
         DBConnection.createTable(SCHEMA);
	 }
}
