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
    private static final String password = "root";
    public static void setup(String schema) {
   	 try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            System.out.println("Successfully Connected.");
            
            String sql = "CREATE DATABASE IF NOT EXISTS CRS";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");   
            
            String CREATE_TABLE_SQL=schema;
            stmt.executeUpdate(CREATE_TABLE_SQL);
            System.out.println("Table created successfully..."); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
