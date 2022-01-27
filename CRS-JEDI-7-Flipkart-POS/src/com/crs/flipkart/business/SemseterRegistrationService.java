package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.DBUtils;
import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;

/**
 * @author nandini 
 */

public class SemseterRegistrationService implements SemesterRegistrationInterface{
	public void addCourse() {
		System.out.println("Course Added!");
	}
	
	public void dropCourse(Course c) {
		System.out.println("Course Removed!");
	}
	
	public void billPayment() {
		System.out.println("Paid!");
	}
	
	


}
