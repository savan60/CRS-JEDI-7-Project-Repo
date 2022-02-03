/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author SAVAN
 *
 * Raised when grade card for semester is not present
 */
//generateGradeCardBySem
public class GradeCardBySemFoundEmpty extends Exception{
	private int sem;
	   public GradeCardBySemFoundEmpty(int sum)
	   {
	      this.sem = sem;
	   } 
	   public int getSem()
	   {
	      return sem;
	   }
}
