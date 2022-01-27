/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;

/**
 * @author SAVAN
 *
 */
public interface SemesterRegistrationInterface {
	public void addCourse();
	public void dropCourse(Course c) ;
	public void billPayment() ;
}
