/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public interface PaymentNotifierDaoInterface {

	public void addPaymentNotification(String studentId, String invoiceId, String message);

}
