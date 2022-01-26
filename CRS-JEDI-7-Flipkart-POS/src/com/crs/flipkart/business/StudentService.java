package com.crs.flipkart.business;

import com.crs.flipkart.dao.CourseDaoOperation;

public class StudentService implements StudentInterface{

	public void selfRegistration() {
		
	}
	public void viewGradeCard() {
		
	}
	public void viewCatalogue(int sem) {
		CourseDaoOperation.viewCourses(sem);
	}
}
