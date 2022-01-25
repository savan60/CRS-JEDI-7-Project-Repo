/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {
	 public static String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.users ("
	         + "AdminId INT NOT NULL,"
	         + "doj DATE NULL,"
	         + "PRIMARY KEY (AdminId))";
}
