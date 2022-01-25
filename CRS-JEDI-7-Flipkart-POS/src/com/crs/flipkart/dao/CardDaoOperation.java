/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public class CardDaoOperation implements CardDaoInterface {

	public static void createTable() {
		String SCHEMA = "CREATE TABLE IF NOT EXISTS CRS.card (" + "cardNumber VARCHAR(20) NOT NULL,"
				+ "cardType enum('DEBIT', 'CREDIT')," + "expiryDate Date NOT NULL," + "cvv int NOT NULL,"
				+ "bankName VARCHAR(20) NOT NULL," + "PRIMARY KEY (cardNumber))";
		DBConnection.createTable(SCHEMA);
	}

}
