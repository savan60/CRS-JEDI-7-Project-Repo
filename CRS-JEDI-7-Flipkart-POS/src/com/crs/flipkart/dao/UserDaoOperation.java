/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.util.Vector;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.utils.SqlUtils;
import com.crs.flipkart.utils.Utils.UserType;

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
	private PreparedStatement statement = null;
	Connection connection = DBConnection.mysqlConnection;
	
	public UserType authenticate(String email,String password) {
		statement=null;
		try {
			String sql = SqlUtils.VIEW_ALL_USER;
			statement = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if(email.equals(resultSet.getString(2)) && password.equals(resultSet.getString(5))) {
					UserService.currentUsedId=resultSet.getString(1);
					return UserType.valueOf(resultSet.getString(6));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return UserType.None;
	}


	@Override
	public String getUserIdByEmailAndPhoneNumber(String email, String phoneNumber) {
		// TODO Auto-generated method stub
		statement=null;
		Connection conn = DBConnection.mysqlConnection;

		try {
			String sql = SqlUtils.GET_USERID_BY_EMAIL_AND_PHONE;
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1,email);
			statement.setString(2,phoneNumber);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				System.out.println("res is "+resultSet.getString(1));
				return resultSet.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return "0";
	}

	@Override
	public boolean updatePassword(String password,String userId) {
		// TODO Auto-generated method stub
		statement=null;
		Connection conn = DBConnection.mysqlConnection;

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
	public boolean checkPasswordByUserId(String userId, String password) {
		// TODO Auto-generated method stub
		statement=null;
		Connection conn = DBConnection.mysqlConnection;

		try {
			String sql = SqlUtils.CHECK_PASSWORD_BY_USERID;
			statement = (PreparedStatement) conn.prepareStatement(sql);
			System.out.println("here after");
			statement.setString(1,password);
			statement.setString(2,userId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				System.out.println("res is "+resultSet.getString(1));
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}
}
