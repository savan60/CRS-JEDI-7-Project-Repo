
package com.crs.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBUtils {
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		
		
        if (connection != null) {
        	try {
        		if (connection.isClosed()) {
                	//System.out.println("Connection was closed...");
                	connection = null;
                	return getConnection();
                } else {
                	//System.out.println("Connection good...");
                	return connection;
                }
        	} catch (SQLException e) {
        		//System.out.println("Error2345: " + e.getMessage());
        		//e.printStackTrace();
        		//connection = null;
        		return getConnection();
        	}
        } else {
            try {
            	//System.out.println("Connection was NULL...");
//            	Properties prop = new Properties();
//                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
//                prop.load(inputStream);
//                String driver = prop.getProperty("driver");
                String driver = "com.mysql.jdbc.Driver";
//                String url = prop.getProperty("url");
                String url = "jdbc:mysql://localhost/";
//                String user = prop.getProperty("user");
                String user ="root";
//                String password = prop.getProperty("password");
                String password ="root";
                //System.out.println(driver + url + user + password);
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                
                
                Statement stmt = connection.createStatement();
                String sql = "CREATE DATABASE IF NOT EXISTS CRS";
                stmt.executeUpdate(sql); 
                
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
//            catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return connection;
        }

    }
	
	
//	private static final String url = "jdbc:mysql://127.0.0.1:3306/";
//    private static final String user = "root";
//    private static final String password = "satak11411";
//    public static Connection mysqlConnection;
//    
  
    
    public static void createTable(String SCHEMA) {
     	Statement stmt;
		try {
			 stmt = getConnection().createStatement();
			 stmt.executeUpdate(SCHEMA);
			 System.out.println("Admin Table created successfully..."); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
	
	


}