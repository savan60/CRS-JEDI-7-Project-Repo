/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constant.SQLQueriesConstant;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.SqlUtils;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.CardType;
import com.crs.flipkart.exceptions.StudentNotFound;
import com.crs.flipkart.exceptions.NegativeAmountException;
/**
 * @author SAVAN
 *
 */
public class PaymentDaoOperation implements PaymentDaoInterface {
	
	private PreparedStatement statement = null;
	private static Logger logger = Logger.getLogger(PaymentDaoOperation.class);
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
	public boolean generatePaymentDetailsForAllStudents(int amount, String message) throws NegativeAmountException{
	
		try {
			
			if (amount<0)
				throw new NegativeAmountException(amount);
			
			Connection conn = DBUtils.getConnection();
			statement = null;
			
			StudentDaoOperation studentDaoOperation = new StudentDaoOperation();
			ArrayList<String> studentIds = studentDaoOperation.getAllStudentIds();
			int count=0;
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
				count++;
			}
			if(count!=0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}
	
	// update specific payment entry in payment table
	public boolean updatePaymentDetails(String studentId, String paymentType) throws StudentNotFound{
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
			logger.debug("response of update is "+row);
			if(row!=0) {
				return true;
			}
			return false;
			
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		throw new StudentNotFound(studentId);
	}

	@Override
	public boolean getListOfPayment(String userId) {
		// TODO Auto-generated method stub
		logger.info("Start of getListofpayment");
		Connection conn = DBUtils.getConnection();
		String sql = SQLQueriesConstant.GET_ALL_PAYMENT_FOR_STUDENT;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				logger.debug(rs.getString(1));
				return true;
			}
			logger.debug("No payment found");
		} catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
		}		
		return false;
	}
	
}
