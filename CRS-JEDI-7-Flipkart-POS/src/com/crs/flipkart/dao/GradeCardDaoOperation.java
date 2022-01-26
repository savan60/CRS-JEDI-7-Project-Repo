/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.*;
import java.util.*;

/**
 * @author SAVAN
 *
 */
public class GradeCardDaoOperation implements GradeCardDaoInterface {
	private PreparedStatement statement = null;
	Connection connection = DBConnection.mysqlConnection;
	
	public static void createTable() {
		String SCHEMA="CREATE TABLE IF NOT EXISTS CRS.gradecard ("
		         + "gradeCardId VARCHAR(20) NOT NULL,"
		         + "studentId VARCHAR(20),"
		         + "semester INT NOT NULL,"
		         + "grade FLOAT,"
		         + "PRIMARY KEY (gradeCardId))";
		DBConnection.createTable(SCHEMA);
	}
	
	public void gradeCardGen(int sem) {
		RegisteredCourseDaoOperation reg=new RegisteredCourseDaoOperation();
		try {
			HashMap<String,Float> map= reg.generateGradeCardBySem(sem);
			
			for(Map.Entry<String, Float> e : map.entrySet()) {
				
				String id=sem+""+ e.getKey();
				System.out.println("");
//				String grade="INSERT INTO CRS.gradecard("
//						+id +"," 
//						+ e.getKey()+ "," 
//						+ sem+"," 
//						+ e.getValue() +")";
				String grade="insert into CRS.gradecard(gradeCardId, studentId, semester, grade) values (?, ?, ?, ?)";
				statement=connection.prepareStatement(grade);
				statement.setString(1, id);
				statement.setString(2, e.getKey());
				statement.setInt(3, sem);
				statement.setFloat(4, e.getValue());
				
				int result=statement.executeUpdate();
				
				System.out.println(result);
				}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
