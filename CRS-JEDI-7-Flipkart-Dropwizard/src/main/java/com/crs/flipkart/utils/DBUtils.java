
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
	
	/**
	 * Creates a connection to database and returns a connection object
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		
        if (connection != null) {
        	try {
        		if (connection.isClosed()) {
                	connection = null;
                	return getConnection();
                } else {
                	return connection;
                }
        	} catch (SQLException e) {
        		return getConnection();
        	}
        } else {
            try {
//            	Properties prop = new Properties();
//                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
//                prop.load(inputStream);
//                String driver = prop.getProperty("driver");
                String driver = "com.mysql.jdbc.Driver";

//                String url = prop.getProperty("url");
                String url = "jdbc:mysql://localhost/";

//                String user = prop.getProperty("user");
                String user = "root";

//                String password = prop.getProperty("password");
                String password = "root";

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
	

    /**
	 * Method to create table using the schema passed as parameter
	 * 
	 * @param SCHEMA
	 * 
	 * 
	 */
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