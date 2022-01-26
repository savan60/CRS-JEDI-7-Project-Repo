package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.DBConnection;
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
	
	public  void viewRegisteredCourses(String studentId,int sem) {
		RegisteredCourseDaoOperation.printRegisteredCourses(studentId, sem);
	}



}
