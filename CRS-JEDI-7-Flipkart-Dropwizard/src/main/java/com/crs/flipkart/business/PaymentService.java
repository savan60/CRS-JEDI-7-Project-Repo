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

	public void paymentNotify() {
		System.out.println("Payment notification sent");
	}

	public boolean askForPayment(int amount,String message) {
		PaymentDaoOperation paymentDaoOperation = new PaymentDaoOperation();
		try {
			return paymentDaoOperation.generatePaymentDetailsForAllStudents(amount, message);
		} catch (NegativeAmountException e) {
			// TODO Auto-generated catch block
			logger.error("Negative amount : " + e.getAmount());
		}
		return false;
	}

	
	@Override
	public boolean makePaymentByNetBanking() {
		// TODO Auto-generated method stub
		try {
			return paymentDaoInterface.updatePaymentDetails(UserService.currentUsedId, "Netbanking");
		} catch (StudentNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean makePaymentByCard(Card card) {
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

	@Override
	public boolean checkForPayment(String id) {
		// TODO Auto-generated method stub
		return paymentDaoInterface.getListOfPayment(id);
	}
	
	

}
