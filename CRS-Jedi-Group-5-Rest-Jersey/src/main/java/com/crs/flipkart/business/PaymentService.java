/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.crs.flipkart.bean.Card;
import com.crs.flipkart.dao.CardDaoInterface;
import com.crs.flipkart.dao.CardDaoOperation;
import com.crs.flipkart.dao.PaymentDaoInterface;
import com.crs.flipkart.dao.PaymentDaoOperation;
import com.crs.flipkart.exceptions.NegativeAmountException;
import com.crs.flipkart.exceptions.StudentNotFound;
import com.crs.flipkart.utils.Utils.CardType;

/**
 * @author SAVAN
 *
 */
public class PaymentService implements PaymentInterface {

	private static Logger logger = Logger.getLogger(PaymentService.class);

	PaymentDaoInterface paymentDaoInterface = new PaymentDaoOperation();
	
	private static volatile PaymentService instance = null;


	/**
	 * 
	 * 
	 * create a instance of PaymentService
	 *
	 * @return instance of the PaymentService
	 * 
	 */
	public static PaymentService getInstance()
	{
		if(instance == null)
		{
			synchronized(PaymentService.class){
				instance = new PaymentService();
			}
		}
		return instance;
	}

	/**
	 * 
	 * Method to show alert when a payment notification is sent successfully
	 * 
	 */
	public void paymentNotify() {
		logger.info("paymentNotify method started");

		System.out.println("Payment notification sent");
	}

	/**
	 * 
	 * Create semester fee payment invoices for all students
	 * 
	 * @param amount, fee amount on invoice
	 * @param message, message by admin
	 * 
	 * @return boolean, true if invoice created successfully to everyone, otherwise false
	 * 
	 * 
	 */
	public boolean askForPayment(int amount,String message) {
		logger.info("askForPayment method started");

		PaymentDaoOperation paymentDaoOperation = new PaymentDaoOperation();
		try {
			return paymentDaoOperation.generatePaymentDetailsForAllStudents(amount, message);
		} catch (NegativeAmountException e) {
			// TODO Auto-generated catch block
			logger.error("Negative amount : " + e.getAmount());
		}
		return false;
	}

	/**
	 * 
	 * To facilitate fee payment via net banking
	 * updates the mode of payment for student fee details 
	 * 
	 * @return boolean true if details are updated successfully otherwise false
	 */
	@Override
	public boolean makePaymentByNetBanking() {
		logger.info("makePaymentByNetBanking method started");

		// TODO Auto-generated method stub
		try {
			return paymentDaoInterface.updatePaymentDetails(UserService.currentUsedId, "Netbanking");
		} catch (StudentNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * To facilitate fee payment via debit/credit card
	 * updates the mode of payment for student fee details 
	 * 
	 * @param Card, card object storing details of the card used for payment
	 * 
	 * @return boolean, true if updates are successful otherwise false
	 */
	@Override
	public boolean makePaymentByCard(Card card) {
		logger.info("makePaymentByCard method started");

		// TODO Auto-generated method stub
		CardDaoInterface cardDaoInterface = new CardDaoOperation();
		cardDaoInterface.addCard(card);

		try {
			return paymentDaoInterface.updatePaymentDetails(UserService.currentUsedId, card.getCardType().toString());
		} catch (StudentNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * To print records of all the payments done by student with studentId id
	 * 
	 * @param id, student id
	 * @return boolean, if results fetched successfully otherwise false
	 * 
	 * 
	 */
	@Override
	public boolean checkForPayment(String id) {
		logger.info("checkForPayment method started");

		// TODO Auto-generated method stub
		return paymentDaoInterface.getListOfPayment(id);
	}
	
	

}
