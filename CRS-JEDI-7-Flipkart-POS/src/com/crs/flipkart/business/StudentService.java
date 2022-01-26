package com.crs.flipkart.business;

import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;
import com.crs.flipkart.dao.SemesterRegistrationDaoInterface;
import com.crs.flipkart.dao.SemesterRegistrationDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperation;

public class StudentService implements StudentInterface{
	SemesterRegistrationDaoInterface semesterRegistration=new SemesterRegistrationDaoOperation();
	RegisteredCourseDaoInterface registeredCourse=new RegisteredCourseDaoOperation();
	
	public void selfRegistration() {
		
	}
	public void viewGradeCard() {
		
	}
	public void viewCatalogue(int sem) {
		CourseDaoOperation.viewCourses(sem);
	}
	
	public void viewRegisteredCourses(int sem) {
		//take course from student table
		registeredCourse.printRegisteredCourses(UserService.currentUsedId, 1);
	}
	public boolean semesterRegistration(int sem) {
		boolean val=semesterRegistration.checkSemAndStudentIdExists(sem, UserService.currentUsedId);
		if(!val) {
			boolean res=semesterRegistration.insertSem(sem, UserService.currentUsedId);
			if(res) {
				System.out.println("Semester Registration Successful");
				return true;
			}
			else {
				System.out.println("Semseter Registration Failed, Try again!");
			}
		}
		else {
			System.out.println("Registration of the semester is already done");
		}
		return false;
	}
	
	public boolean addCourse(String courseId,int sem) {
		boolean val=registeredCourse.addCourse(courseId,UserService.currentUsedId,sem);
		if(val) {
			System.out.println("Course added successfully");
			return true;
		}
		else {
			System.out.println("Course is not added, Try again");
		}
		return false;
	}
	
	
}
