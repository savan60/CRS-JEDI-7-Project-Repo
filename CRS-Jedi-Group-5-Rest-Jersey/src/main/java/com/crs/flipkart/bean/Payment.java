/**
 * 
 */
package com.crs.flipkart.bean;


/**
 * @author SAVAN
 *
 */
public class Payment {
	public Payment(String studentId, String invoiceId, double amount, boolean status) {
		super();
		this.studentId = studentId;
		this.invoiceId = invoiceId;
		this.amount = amount;
		this.status = status;
	}
	public Payment() {
		// TODO Auto-generated constructor stub
		super();
	}
	private String studentId;//fk
	private String  invoiceId;//pk
	private double amount;
	private boolean status; 
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the invoiceId
	 */
	public String getInvoiceId() {
		return invoiceId;
	}
	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
