/**
 * 
 */
package com.crs.flipkart.exceptions;


/**
 * @author DELL NOTEBOOK
 *
 * Raised when amount of money is less than required amount
 */
public class NegativeAmountException extends Exception {

	private float amount;
	
	public NegativeAmountException(float amt)
	   {
	      this.amount = amt;
	   } 
	public float getAmount()
	   {
	      return amount;
	   }
	
}
