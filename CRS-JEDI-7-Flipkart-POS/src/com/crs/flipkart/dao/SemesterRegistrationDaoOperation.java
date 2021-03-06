/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.crs.flipkart.business.SemseterRegistrationService;
import com.crs.flipkart.exceptions.CheckForSemesterRegistration;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.SqlUtils;
import com.crs.flipkart.utils.Utils;

/**
 * @author SAVAN
 *
 */
public class SemesterRegistrationDaoOperation implements SemesterRegistrationDaoInterface{
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.semesterRegistration ("
	            + "semesterRegistrationId VARCHAR(50) NOT NULL,"
		     	+ "studentId VARCHAR(20) NULL,"
	            + "semester INT NOT NULL,"
	            + "date  DATE NOT NULL,"
	            + "PRIMARY KEY (semesterRegistrationId))";
		DBUtils.createTable(SCHEMA);
	}

	private static Logger logger = Logger.getLogger(SemesterRegistrationDaoOperation.class);

	
	
	private PreparedStatement statement = null;
	Connection connection = DBUtils.getConnection();
	
	@Override
	public boolean checkSemAndStudentIdExists(int sem, String studentId) throws CheckForSemesterRegistration{
		// TODO Auto-generated method stub
		statement=null;
		Connection conn = DBUtils.getConnection();
		try {
			String sql = SqlUtils.CHECK_SEM_AND_STUDENTID_EXISTS;
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1,studentId);
			statement.setInt(2,sem);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				throw new CheckForSemesterRegistration(sem);
			}
		} catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean insertSem(int sem, String studentId) {
		// TODO Auto-generated method stub
		statement=null;
		Connection conn = DBUtils.getConnection();
		try {
			String sql = SqlUtils.INSERT_SEM_RESGISTRAION;
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1,Utils.generateId());
			statement.setString(2,studentId);
			statement.setInt(3, sem);
			int res = statement.executeUpdate();
			if(res==1) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
		}
		return false;
	}
	
	
	
	
}
