package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;

public interface StudentInterface {
	public void viewGradeCard();
	public void selfRegistration(Student student);
	public String viewCatalogue(int sem) ;	
	public boolean semesterRegistration(int sem);
	public boolean addCourse(String courseId,int sem);
	public String viewRegisteredCourses(int sem);
	public boolean dropCourse(String studentId, String courseId);
	public void setSemester(String currentUsedId, int sem);
	public int getSemester(String id);
}
