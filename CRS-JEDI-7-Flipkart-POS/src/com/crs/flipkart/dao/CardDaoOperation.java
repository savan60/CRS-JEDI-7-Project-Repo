/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.bean.Card;
import com.crs.flipkart.utils.Utils;

/**
 * @author SAVAN
 *
 */
public class CardDaoOperation implements CardDaoInterface {

	private PreparedStatement statement = null;
	Connection connection = DBConnection.mysqlConnection;
	
	
	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.card (" + "cardNumber VARCHAR(20) NOT NULL,"
				+ "cardType enum('DEBIT', 'CREDIT')," + "expiryMonth INT NOT NULL," + "expiryYear INT NOT NULL," 
				+ "bankName VARCHAR(20) NOT NULL," + "PRIMARY KEY (cardNumber))";
		DBConnection.createTable(SCHEMA);
	}
	
	public void addCard(Card card) {
		
		try {
			DBConnection.setup();
			Connection conn = DBConnection.mysqlConnection;
			statement = null;
				
			String sql = "INSERT INTO `CRS`.`card` (`cardNumber`, `cardType`, `expiryMonth`, `expiryYear`, `bankName`) VALUES (?, ?, ?, ?, ?);";
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setString(1, card.getCardNumber());
			statement.setString(2, card.getCardType().toString());
			statement.setInt(3, card.getExpiryMonth());
			statement.setInt(4, card.getExpiryYear());
			statement.setString(5, card.getBankName());
			statement.execute();
			
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}

}
