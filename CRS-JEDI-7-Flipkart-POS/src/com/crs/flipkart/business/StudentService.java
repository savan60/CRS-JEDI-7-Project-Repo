package com.crs.flipkart.business;

import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.UserType;

public class StudentService implements StudentInterface{

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
	public void viewCatalogue() {
		
	}

	
//	public static void main(String[] arg) {
//		StudentService service = new StudentService();
//		service.selfRegistration();
//	}
	
}
