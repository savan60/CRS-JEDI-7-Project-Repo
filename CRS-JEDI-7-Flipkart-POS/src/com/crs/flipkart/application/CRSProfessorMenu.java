package com.crs.flipkart.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.business.CourseService;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.utils.Utils.UserType;

public class CRSProfessorMenu {
	
	Scanner sc=new Scanner(System.in);
	UserInterface user=new UserService();
	
	public void homePage() {

		System.out.println("Welcome to Professor portal!");
		
		ProfessorInterface professorService=new ProfessorService();
		while(true) {
			System.out.println("__________________________________________________________");
			
			System.out.println("Please select operation to perform:\n1. View Enrolled Students\n2. Assign grades\n3. Add Courses\n4. View Courses\n5. Update password\n6. Logout");
			//update password will be here
			int ch=sc.nextInt();
			
			if(ch==6) {
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
					
				case 5:
					System.out.println("Enter your old password\n");
					String password=sc.next();
					UserType val1=user.authenticate(user.getEmailByUserId(UserService.currentUsedId),password);
					if(!(val1==UserType.None)) {
						while(true) {
							System.out.println("Enter your choice:\n"+"1.Type new password \n2. Exit");
							int choice = sc.nextInt();
							
							if(choice == 2) break;
							else if(choice != 1) 
								System.out.println("Invalid Choice");
							else {
								String pass1, pass2; 
								//System.out.println("Type New Password!");
								pass1 = sc.next();
								System.out.println("Re-Enter New Password!");
								pass2 = sc.next();
								if(pass1.equals(pass2)) {
									user.createNewPassword(pass1,UserService.currentUsedId);
									System.out.println("Passowrd changed. Login!");
									break;
								}
								else System.out.println("Passowrd Mismatch. Try Again!");
							}
						}
					}
					else {
						System.out.println("Invalid credentials");
					}
					break;
				default: System.out.println("Enter valid choice");
				
			}
		}
	}
	
	
	
}
