/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.constant.SQLQueriesConstant;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.Utils;

/**
 * @author SAVAN
 *
 */
public class PaymentNotifierDaoOperation implements PaymentNotifierDaoInterface {
	
	private PreparedStatement statement = null;
	
	// create paymentNotifier table if it not exists
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.paymentNotifier ("
				+ "invoiceId VARCHAR(45) NOT NULL,"
				+ "studentId VARCHAR(20) NOT NULL,"
				+ "message VARCHAR(45) NOT NULL,"
				+ "PRIMARY KEY (invoiceId))";
				
		DBUtils.createTable(SCHEMA);
	}
	
	// add Payment Notification details to PaymentNotifier table
	public void addPaymentNotification(String studentId, String invoiceId, String message) {
		
		try {
			Connection conn = DBUtils.getConnection();
			statement = null;
		
			String sql = SQLQueriesConstant.insertPaymentNotifierQuery;
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1, studentId);
			statement.setString(2, invoiceId);
			statement.setString(3, message);
			statement.execute();
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
}
