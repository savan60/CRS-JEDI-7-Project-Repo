/**
 * 
 */
package com.crs.flipkart.dao;
import java.sql.*;

/**
 * @author SAVAN
 *
 */
public class DBConnection {
	private static final String url = "jdbc:mysql://localhost/";
    private static final String user = "root";
    private static final String password = "Root@123";
    public static Connection mysqlConnection;
    public static void setup() {
   	 try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS CRS";
            stmt.executeUpdate(sql); 
            mysqlConnection=conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void createTable(String SCHEMA) {
     	Statement stmt;
		try {
			stmt = mysqlConnection.createStatement();
			 stmt.executeUpdate(SCHEMA);
			 System.out.println("Admin Table created successfully..."); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
