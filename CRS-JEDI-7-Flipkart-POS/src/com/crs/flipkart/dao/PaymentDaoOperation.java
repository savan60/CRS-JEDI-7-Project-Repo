/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class PaymentDaoOperation implements PaymentDaoInterface {
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.payment ("
		         + "studentId VARCHAR(20) NOT NULL,"
		         + "invoiceId VARCHAR(20) NOT NULL,"
		         + "amount INT NOT NULL,"
		         + "PRIMARY KEY (invoiceId),"
		         + "FOREIGN KEY (studentId) REFERENCES student(studentId))";
		DBConnection.createTable(SCHEMA);
	}
}
