/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.exceptions.NegativeAmountException;
import com.crs.flipkart.exceptions.StudentNotFound;

/**
 * @author SAVAN
 *
 */
public interface PaymentDaoInterface {
	
	public boolean updatePaymentDetails(String studentId, String paymentType) throws StudentNotFound;
	public boolean generatePaymentDetailsForAllStudents(int amount, String message) throws NegativeAmountException;
	public boolean getListOfPayment(String userId);
}
