package com.crs.flipkart.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.business.CourseService;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.utils.Utils.UserType;

public class CRSProfessorMenu {
	
	Scanner sc=new Scanner(System.in);
	UserService user=new UserService();
	
	public void entry() {
		
		while(true) {
			System.out.println("__________________________________________________________");

			System.out.println("Enter your choice:\n"+"1. Login\n2. Forgot password\n3. Update Password\n4. Return to previous options");
			
			int ch=sc.nextInt();
			if(ch==4) 
				break;
			
			String email,password;
		
			switch(ch) {
			
				case 1: System.out.println("Enter your email:\n");
						email=sc.next();
						System.out.println("Enter your password\n");
						password=sc.next();
						boolean val=user.authenticate(UserType.Professor,email,password);
						if(val) {
							System.out.println("Login Successful");
							homePage();
						}
						else {
							System.out.println("Login Unsuccessful, please try again!");
						}
						break;
						
				case 2: System.out.println("Enter your email:\n");
						email=sc.next();
						user.forgotPassword(UserType.Professor,email);
						break;
					
				case 3: System.out.println("Enter your email:\n");
						email=sc.next();
						System.out.println("Enter your password\n");
						password=sc.next();
						boolean val1=user.authenticate(UserType.Professor,email,password);
						if(val1) {
							user.updatePassword(UserType.Professor, email);
						}
						else System.out.println("Wrong Email or Password. Try Again!");
						break;
				
				default: System.out.println("Enter valid choice");
				
			}

		}
	}
	
	private void homePage() {

		System.out.println("Welcome to Professor portal!");
		
		ProfessorService professorService=new ProfessorService();
		while(true) {
			System.out.println("__________________________________________________________");

			System.out.println("Please select operation to perform:\n1. View Enrolled Students\n2. Assign grades\n3. Add Courses\n4. View Courses\n0. Logout");
			int ch=sc.nextInt();
			
			if(ch==0) {
				System.out.println("Thank you");
				break;
			}
			
			switch(ch) {
				case 1: 
					professorService.viewEnrolledStudents(UserService.currentUsedId);
					break;
				case 2: //call assign grades
					professorService.viewEnrolledStudents(UserService.currentUsedId);
					System.out.println("Enter studentId: ");
					String studentId=sc.next();
					System.out.println("Enter grade of studentId "+studentId+": ");
					float newGrade=sc.nextFloat();
					System.out.println("Enter courseId: ");
					String courseId = sc.next();
					professorService.addGrade(UserService.currentUsedId,newGrade,studentId,courseId);
					
					break;
					
				case 3:
					CourseService courseService = new CourseService();
					HashMap<String, Course> listOfCourses = courseService.getCourses();
					
					
					for (Map.Entry<String,Course> entry : listOfCourses.entrySet()) {
				            System.out.println("CourseId = " + entry.getKey() +
				                           ", CourseName = " + entry.getValue().getName());
					}
					
					System.out.println("Enter the courseId?");
					String cId = sc.next();
					
					professorService.addCourse(UserService.currentUsedId, cId);
					break;
					
				case 4: 
					professorService.viewCourse(UserService.currentUsedId);
					break;
					
				default: System.out.println("Enter valid choice");
				
			}
		}
	}
	
	
	
}
