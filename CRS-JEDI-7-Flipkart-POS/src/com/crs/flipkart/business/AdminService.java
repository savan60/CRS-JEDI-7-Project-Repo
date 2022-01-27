/**
 * 
 */
package com.crs.flipkart.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.crs.flipkart.utils.Utils.UserType;
import com.crs.flipkart.dao.DBConnection;
import java.util.Date;

import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.dao.*;

/**
 * @author Shruti
 *
 */
public class AdminService implements AdminInterface {
	
	UserService user=new UserService();
	AdminDaoOperation admin=new AdminDaoOperation();
	
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
		
//	public void addCourse() {
//		
//	}
//	
//	public void removeCourse() {
//		
//	}
	
	public void approveAllStudents() {
		admin.approveStudents(1);
	}
	public void approveStudentsOneByOne() {
		admin.approveStudents(0);
	}
	
	public void genReportCard(int sem) {
		GradeCardDaoOperation grade=new GradeCardDaoOperation();
		
		grade.gradeCardGen(sem);
	}
	
	public void updateAddDropTime() {
		
	}

	

}