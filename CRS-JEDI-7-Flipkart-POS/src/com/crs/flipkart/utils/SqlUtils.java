/**
 * 
 */
package com.crs.flipkart.utils;

/**
 * @author SAVAN
 *
 */
public class SqlUtils {
	
	//User
	public static String VIEW_ALL_USER="select * from CRS.user";
	public static String GET_EMAIL_BY_USERID="select email from CRS.user where userId = ?";
	public static String GET_USERID_BY_EMAIL_AND_PHONE="select userId from CRS.user where email = ? and phoneNumber = ?";
	public static String UPDATE_PASSWORD="update CRS.user set password = ? where userId = ?";
	public static String CHECK_PASSWORD_BY_USERID="select userId from CRS.user where password = ? and userId = ?";
	
	//Semster Registraion
	public static String CHECK_SEM_AND_STUDENTID_EXISTS="select studentId from CRS.semesterRegistration where studentId = ? and semester = ?";
	public static String INSERT_SEM_RESGISTRAION="insert into CRS.semesterRegistration(semesterRegistrationId,studentId, semester,date) values (?,?, ?,curdate())";
	
	// Add User to database
	public static String INSERT_USER="insert into CRS.user(userId,email,phoneNumber,address,password,userType) values (?,?,?,?,?,?)";
	
	// Add Professor to database
	public static String INSERT_PROFESSOR="insert into CRS.professor(professorId,dept,doj,pos) values (?,?,?,?)";
	
}

