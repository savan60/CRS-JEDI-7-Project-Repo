/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.exceptions.NegativeAmountException;
import com.crs.flipkart.exceptions.StudentNotFound;

/**
 * @author SAVAN
 *
 */
public interface PaymentDaoInterface {
	
	public boolean updatePaymentDetails(String studentId, String paymentType) throws StudentNotFound;
	public void generatePaymentDetailsForAllStudents(int amount, String message) throws NegativeAmountException;

}
