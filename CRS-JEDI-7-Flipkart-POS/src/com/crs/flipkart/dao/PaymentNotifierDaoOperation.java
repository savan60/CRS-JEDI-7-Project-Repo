/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class PaymentNotifierDaoOperation implements PaymentNotifierDaoInterface {
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.paymentNotifier ("
				+ "invoiceId VARCHAR(20) NOT NULL,"
				+ "studentId VARCHAR(20) NOT NULL,"
				+ "message VARCHAR(45) NOT NULL,"
				+ "PRIMARY KEY (invoiceId))";
				
		DBConnection.createTable(SCHEMA);
	}
}
