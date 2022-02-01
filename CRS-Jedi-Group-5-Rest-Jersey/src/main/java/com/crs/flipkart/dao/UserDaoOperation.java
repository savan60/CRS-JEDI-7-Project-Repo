/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.exceptions.PasswordNotMatchException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.exceptions.phoneNumberNotMatchException;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.SqlUtils;
import com.crs.flipkart.utils.Utils.UserType;
import java.sql.*;

/**
 * @author SAVAN
 *
 */


public class UserDaoOperation implements UserDaoInterface{
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.user("
		         + "userId VARCHAR(20) NOT NULL,"
		         + "email VARCHAR(20) NOT NULL," 
		         + "password VARCHAR(20) NOT NULL," +"userType enum('Admin', 'Student','Professor'),"
		         + "PRIMARY KEY (userId))";
		DBUtils.createTable(SCHEMA);
	}
	
	private PreparedStatement statement = null;
	Connection connection = DBUtils.getConnection();
	
	public User authenticate(String email,String password) throws UserNotFoundException, PasswordNotMatchException {
		statement=null;
       
        User user=new User();
		try {
			String sql = SqlUtils.VIEW_ALL_USER;
			statement = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if(email.equals(resultSet.getString(2))) {
					if(password.equals(resultSet.getString(3))){
						user.setUserId(resultSet.getString(1));
						user.setUserType(UserType.valueOf(resultSet.getString(4)));						
						return user;
					}
					else {
						throw new PasswordNotMatchException(email);
					}
				}
			}
			throw new UserNotFoundException(email);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}


	@Override
	public String getUserIdByEmailAndPhoneNumber(String email, String phoneNumber) throws UserNotFoundException, phoneNumberNotMatchException {
		statement=null;
		try {
			String sql=SqlUtils.VIEW_USER_WITH_PHONE;
			statement = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if(email.equals(resultSet.getString(2))) {
					if(phoneNumber.equals(resultSet.getString(3))){
						return resultSet.getString(1);
					}
					else {
						throw new phoneNumberNotMatchException(email);
					}
				}
			}
			throw new UserNotFoundException(email);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return "0";
	}

	@Override
	public boolean updatePassword(String password,String userId) {
		statement=null;
		
		Connection conn = DBUtils.getConnection();
		try {
			String sql = SqlUtils.UPDATE_PASSWORD;
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1,password);
			statement.setString(2,userId);
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

	@Override
	public boolean checkPasswordByUserId(String userId, String password) throws UserNotFoundException, PasswordNotMatchException {
		statement=null;
		
		try {
			String sql = SqlUtils.VIEW_ALL_USER;
			statement = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if(userId.equals(resultSet.getString(1))) {
					if(password.equals(resultSet.getString(3))){
						UserService.currentUsedId=resultSet.getString(1);
						return true;
					}
					else {
						throw new PasswordNotMatchException(userId);
					}
				}
			}
			throw new UserNotFoundException(userId);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return false;
	}


	public void addUser(User user) {

		try {
			Connection conn = DBUtils.getConnection();
			statement = null;
			String sql = SqlUtils.INSERT_USER;
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1,user.getUserId());
			statement.setString(2,user.getEmail());
			statement.setString(3,user.getPassword());
			statement.setString(4, user.getUserType().name());
			statement.execute();
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}


	@Override
	public boolean IsStudentApproved(String userId) {
		// TODO Auto-generated method stub
		statement=null;
		
		try {
			String sql = SqlUtils.IS_STUDENT_APPROVED;
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1,userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				boolean isApproved=resultSet.getBoolean(1);
				if(isApproved) {
					logger.debug("Student is approved");
					return true;
				}
				break;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}		
		logger.debug("student is not approved");
		return false;
	}
}
