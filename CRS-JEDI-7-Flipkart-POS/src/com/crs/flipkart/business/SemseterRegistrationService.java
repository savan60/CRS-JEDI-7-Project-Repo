package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;

/**
 * @author nandini 
 */

public class SemseterRegistrationService {
	
	public void addCourse() {
		System.out.println("Course Added!");
	}
	
	public void dropCourse(Course c) {
		System.out.println("Course Removed!");
	}
	
	public void billPayment() {
		System.out.println("Paid!");
	}
	
	public void viewRegisteredCourses() {
		System.out.println("Registered Courses!");
	}
}
