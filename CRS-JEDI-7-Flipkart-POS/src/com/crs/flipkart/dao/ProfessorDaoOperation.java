/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;

/**
 * @author SAVAN
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {
	public static ArrayList<Professor> professors = new ArrayList<>();

	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.professor (" + "professorId VARCHAR(20) NOT NULL,"
				+ "dept VARCHAR(10) NOT NULL," + "doj DATE NOT NULL," + "pos VARCHAR(10),"
				+ "PRIMARY KEY (professorId))";
		DBConnection.createTable(SCHEMA);
	}

	public static void app() {
		Connection conn = DBConnection.mysqlConnection;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from CRS.professor");

			while (rs.next()) {
				Professor professor = new Professor(rs.getString("professorId"), rs.getString("dept"),
						rs.getDate("doj"), rs.getString("pos"));
				professors.add(professor);
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}