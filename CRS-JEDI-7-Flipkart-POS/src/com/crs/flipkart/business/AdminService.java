/**
 * 
 */
package com.crs.flipkart.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.crs.flipkart.utils.Utils.UserType;
import com.crs.flipkart.dao.DBConnection;

/**
 * @author parth
 *
 */
public class AdminService implements AdminInterface {
	
	
	public boolean addProfessor(String userId, String email, String phoneNumber, String address, String password, String department, String position) {
		Connection conn = DBConnection.mysqlConnection;
		Statement stmt = null;
		int userres = 0, profres=0;
	
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userquery = "INSERT INTO crs.user VALUES('"+userId+"','"+email+"','"+phoneNumber+"','"+address+"','"+password+"','"+UserType.Professor+"');";
		String profquery = "INSERT INTO crs.professor VALUES('"+userId+"','"+department+"',curdate(),'"+position+"');";

		try {
			userres = stmt.executeUpdate(userquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			profres = stmt.executeUpdate(profquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(userres+" "+profres);
		if(userres==0 || profres==0) return false;
		return true;
	}
	
	public void verifyStudent() {
			
	}
		
	public void addCourse() {
		
	}
	
	public void removeCourse() {
		
	}
	
	
	public void getReportCard() {
		
	}
	
	public void updateAddDropTime() {
		
	}

}
