/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;

/**
 * @author SAVAN
 *
 */
public interface CatalogInterface {
public void addCourse(Course newCourse) ;
	
	public void cancelCourse(Course dropCourse) ;
	public void viewCourse() ;
}
