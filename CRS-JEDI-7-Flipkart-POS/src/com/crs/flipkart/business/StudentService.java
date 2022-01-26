package com.crs.flipkart.business;

import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;
import com.crs.flipkart.dao.SemesterRegistrationDaoInterface;
import com.crs.flipkart.dao.SemesterRegistrationDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperation;
import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.UserType;

public class StudentService implements StudentInterface{
	SemesterRegistrationDaoInterface semesterRegistration=new SemesterRegistrationDaoOperation();
	RegisteredCourseDaoInterface registeredCourse=new RegisteredCourseDaoOperation();
	//static variable for semester
	public void selfRegistration() {
		
		Scanner sc = new Scanner(System.in);
		
		String studentId = Utils.generateUniqueId().substring(0,3) + Utils.generateUniqueId().substring(10,13);
		
		System.out.println("Enter name: ");
		String name = sc.next();
		
		System.out.println("Enter email address: ");
		String email = sc.next();
		
		System.out.println("Enter phonenumber: ");
		long phoneNumber = sc.nextLong();
		
		System.out.println("Enter address: ");
		String address = sc.next();
		
		System.out.println("Enter password: ");
		String password = sc.next();
		
		Student student = new Student(studentId, name, phoneNumber, address, UserType.Student, password);
		
		StudentDaoOperation studentDaoOperation = new StudentDaoOperation();
		studentDaoOperation.addStudent(student);
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
