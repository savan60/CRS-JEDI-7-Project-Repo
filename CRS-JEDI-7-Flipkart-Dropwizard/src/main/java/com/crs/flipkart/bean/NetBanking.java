/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author SAVAN
 *
 */

//No database
public class NetBanking extends Payment{
	public NetBanking(String studentId, String invoiceId, double amount, boolean status) {
		super(studentId, invoiceId, amount, status);
		// TODO Auto-generated constructor stub
	}
	private String bankName;
	private String accountHolderName;
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the accountHolderName
	 */
	public String getAccountHolderName() {
		return accountHolderName;
	}
	/**
	 * @param accountHolderName the accountHolderName to set
	 */
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
}
