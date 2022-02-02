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
 
	public enum LoginStatus{
		Success,
		Failure,
		NotApproved
	}
	public static String generateId() {
		String id=(UUID.randomUUID()).toString();
		return id;
	}
	
	public enum CardType{
		DEBIT,CREDIT;
	}

	
	public static String generateUniqueId() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID;
	}
	
//	public static void main(String[] arg) {
//		for(int i=0; i<10; i++)
//			System.out.println(Utils.generateUniqueId());
//	}

}
