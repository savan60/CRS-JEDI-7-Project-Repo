package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exceptions.AddCourseLimitExceed;

public interface StudentInterface {
	public void viewGradeCard();
	public boolean selfRegistration(Student student);
	public String viewCatalogue(int sem) ;	
	public boolean semesterRegistration(int sem);
	public boolean addCourse(String courseId,int sem) throws AddCourseLimitExceed;
	public String viewRegisteredCourses(int sem);
	public boolean dropCourse(String studentId, String courseId);
	public void setSemester(String currentUsedId, int sem);
	public int getSemester(String id);
}
