/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public interface PaymentDaoInterface {
	
	
	public void generatePaymentDetailsForAllStudents(int amount, String message);
	public boolean updatePaymentDetails(String studentId, String paymentType);

}
