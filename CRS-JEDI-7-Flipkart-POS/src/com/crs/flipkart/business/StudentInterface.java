package com.crs.flipkart.business;

public interface StudentInterface {
	
	public void selfRegistration();
	
	public void viewGradeCard();

	public void viewCatalogue(int sem) ;	
	public boolean semesterRegistration(int sem);
	public boolean addCourse(String courseId,int sem);
	public void viewRegisteredCourses(int sem);

	public void setSemester(String currentUsedId, int sem);
	public boolean dropCourse(String StudentId, String courseId);
}
