/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constant.SQLQueriesConstant;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.SqlUtils;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.CardType;

/**
 * @author SAVAN
 *
 */
public class PaymentDaoOperation implements PaymentDaoInterface {
	
	private PreparedStatement statement = null;
	Connection connection = DBUtils.getConnection();
	
	// create payment table if it not exists
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.payment ("
		         + "studentId VARCHAR(20) NULL,"
		         + "invoiceId VARCHAR(45) NOT NULL,"
		         + "amount DOUBLE NOT NULL,"
		         + "status BOOLEAN NOT NULL,"
		         + "type enum('DEBIT', 'CREDIT', 'Netbanking'),"
		         + "date DATE,"
		         + "PRIMARY KEY (invoiceId))";
		         
		DBUtils.createTable(SCHEMA);
	}
	
	// populate Payment details for all the student in payment table
	public void generatePaymentDetailsForAllStudents(int amount, String message) {
	
		try {
			Connection conn = DBUtils.getConnection();
			statement = null;
			
			StudentDaoOperation studentDaoOperation = new StudentDaoOperation();
			ArrayList<String> studentIds = studentDaoOperation.getAllStudentIds();
			
			for(String studentId : studentIds) {
				String invoiceId = Utils.generateUniqueId();
				String sql = SQLQueriesConstant.insertPaymentQuery;
				statement = (PreparedStatement) conn.prepareStatement(sql);
				statement.setString(1, studentId);
				statement.setString(2, invoiceId);
				statement.setInt(3, amount);
				statement.setBoolean(4, false);
				statement.execute();
				
				PaymentNotifierDaoInterface paymentNotifierDaoInterface = new PaymentNotifierDaoOperation();
				paymentNotifierDaoInterface.addPaymentNotification(studentId, invoiceId, message);	
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	// update specific payment entry in payment table
	public boolean updatePaymentDetails(String studentId, String paymentType) {
		try {
			Connection conn = DBUtils.getConnection();
			statement = null;
			
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				
			String sql = SQLQueriesConstant.updatePaymentQuery;
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setBoolean(1, true);
			statement.setString(2, paymentType);
			statement.setTimestamp(3, date);
			statement.setString(4, studentId);
			int row = statement.executeUpdate();
			if(row==0) {
				return false;
			}
			else {
				return true;
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return false;
	}
	
	
	
	
}
