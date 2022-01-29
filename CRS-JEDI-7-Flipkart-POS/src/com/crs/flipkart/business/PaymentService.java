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
public class PaymentService implements PaymentInterface{
	
	private static Logger logger = Logger.getLogger(PaymentService.class);
	public void paymentNotify() {
		System.out.println("Payment notification sent");
	}
	
	public void askForPayment() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter fee amount: ");
		int amount = sc.nextInt();
		System.out.println("Enter payment message: ");
		String message = sc.next();
		
		
		PaymentDaoOperation paymentDaoOperation = new PaymentDaoOperation();
		try {
			paymentDaoOperation.generatePaymentDetailsForAllStudents(amount, message);
		} catch (NegativeAmountException e) {
			// TODO Auto-generated catch block
			logger.error("Negative amount : " + e.getAmount());
		}
	}
	
	
	public boolean makePayment() {
		
		PaymentDaoInterface paymentDaoInterface = new PaymentDaoOperation();
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose type of the payment: ");
		System.out.println("1. Credit Card/Debit Card");
		System.out.println("2. Netbanking");
		int type = sc.nextInt();
		
		
		
//		System.out.println("Enter studentId:");
//		String studentId = sc.next();
		String studentId = UserService.currentUsedId;
		
		switch(type) {
			case 1:
						
				System.out.println("Enter cardNumber:");
				String cardNumber = sc.next();
				
				System.out.println("Enter card type\n"
						+ "  press 1 for DEBIT card\n"
						+ "  press 2 for CREDIT card\n");
				int choice = sc.nextInt();
				CardType cardType = (choice == 1 ? CardType.DEBIT : CardType.CREDIT);  
				
				System.out.println("Enter Expiry month: ");
				int month = sc.nextInt();
				
				System.out.println("Enter Expiry year: ");
				int year = sc.nextInt();
				

				System.out.println("Enter cvv: ");
				int cvv = sc.nextInt();
				
				System.out.println("Enter bank name: ");
				String bankName = sc.next();
				
				Card card = new Card(cardNumber, cardType, month, year, bankName);
				CardDaoInterface cardDaoInterface = new CardDaoOperation();
				cardDaoInterface.addCard(card);
				
				// Reirecting to enter otp
				
				// Let's assume payment is successful 
				
				// Update payment table
				
			try {
				paymentDaoInterface.updatePaymentDetails(studentId, cardType.toString());
			} catch (StudentNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			break;
				
			case 2:

				// Reirecting to NETBANKING
				
				// Let's assume payment is successful 
				
				// Update payment table
			try {
				paymentDaoInterface.updatePaymentDetails(studentId, "Netbanking");
			} catch (StudentNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			break;
				
			default:
				System.out.println("Choose proper payment option.");
		}
		
		return false;
	}
	
}
