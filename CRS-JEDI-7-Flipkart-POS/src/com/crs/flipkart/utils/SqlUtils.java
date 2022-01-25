/**
 * 
 */
package com.crs.flipkart.utils;

/**
 * @author SAVAN
 *
 */
public class SqlUtils {
	public static String VIEW_ALL_USER="select * from CRS.user";
	public static String GET_EMAIL_BY_USERID="select email from CRS.user where userId = ?";
	public static String GET_USERID_BY_EMAIL_AND_PHONE="select userId from crs.user where email = ? and phoneNumber = ?";
	public static String UPDATE_PASSWORD="update crs.user set password = ? where userId = ?";
	public static String CHECK_PASSWORD_BY_USERID="select userId from crs.user where password = ? and userId = ?";
}

