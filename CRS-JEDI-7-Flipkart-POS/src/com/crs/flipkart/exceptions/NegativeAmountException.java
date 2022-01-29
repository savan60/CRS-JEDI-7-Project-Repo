/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author DELL NOTEBOOK
 *
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
