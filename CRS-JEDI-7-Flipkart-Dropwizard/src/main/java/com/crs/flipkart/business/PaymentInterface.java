/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Card;

/**
 * @author SAVAN
 *
 */
public interface PaymentInterface{
	public void paymentNotify();
	public boolean askForPayment(int amount,String message);
	public boolean makePaymentByNetBanking();
	public boolean makePaymentByCard(Card card);
	public boolean checkForPayment(String id);

}
