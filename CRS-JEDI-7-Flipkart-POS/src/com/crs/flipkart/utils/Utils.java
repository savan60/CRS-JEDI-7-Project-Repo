package com.crs.flipkart.utils;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

/**
 * @author SAVAN
 *
 */
public class Utils {

	public enum UserType{
		Admin,
		Professor,
		Student,
		None
	}
 
	public static String generateId() {
		String id=(UUID.randomUUID()).toString();
		return id;
	}
}
