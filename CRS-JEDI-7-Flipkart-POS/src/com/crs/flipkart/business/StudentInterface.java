package com.crs.flipkart.business;

public interface StudentInterface {
	
	public void selfRegistration();
	
	public void viewGradeCard();

	public void viewCatalogue();
	
	public boolean semesterRegistration(int sem);
	public boolean addCourse(String courseId,int sem);

}
