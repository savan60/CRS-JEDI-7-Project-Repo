/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author nikhil
 *
 */
public class PaymentNotifier {
	private String StudentID;//fk
	private String invoiceID;//pk & fk
	private String message;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the studentID
	 */
	public String getStudentID() {
		return StudentID;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	/**
	 * @return the invoiceID
	 */
	public String getInvoiceID() {
		return invoiceID;
	}
	/**
	 * @param invoiceID the invoiceID to set
	 */
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	
	

}
