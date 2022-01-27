/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.constant.SQLQueriesConstant;
import com.crs.flipkart.utils.DBUtils;

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
		DBUtils.createTable(SCHEMA);
	}

	public static void app() {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt=null;
		try {
			stmt = (PreparedStatement) conn.prepareStatement(SQLQueriesConstant.selectAllProfessorsQuery);

			ResultSet rs = stmt.executeQuery();

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