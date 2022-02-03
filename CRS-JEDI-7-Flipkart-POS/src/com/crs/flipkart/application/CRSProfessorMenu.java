package com.crs.flipkart.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.business.CourseService;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.constant.COLORCONSTANT;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.utils.Pair;
import com.crs.flipkart.utils.Utils.UserType;

public class CRSProfessorMenu {

	Scanner sc = new Scanner(System.in);
	UserInterface user = new UserService();
	ProfessorInterface professorService = new ProfessorService();

	public void homePage() {

		System.out.println(COLORCONSTANT.TEXT_PURPLE);
		System.out.println("_____________________________________________________________________________________________________________________________________");
		System.out.println();
		System.out.println("#--------------------------------------------WELCOME TO PROFESSOR MENU-------------------------------------------------------------------#");

		System.out.println("_____________________________________________________________________________________________________________________________________");
		
		while (true) {
			System.out.println(COLORCONSTANT.TEXT_CYAN);
			System.out.println(
					"Please select operation to perform:\n1. View Enrolled Students\n2. Assign grades\n3. Add Courses\n4. View Courses\n5. Update password\n6. Logout".toUpperCase());
			// update password will be here
			System.out.println(COLORCONSTANT.TEXT_GREEN);
			System.out.print("Enter Choice: ".toUpperCase());
			int ch = sc.nextInt();

			if (ch == 6) {
				System.out.println("Thank you");
				break;
			}
			
			switch (ch) {
				case 1:
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					viewEndrolledStudents();
					break;
				case 2: // call assign grades
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					viewEndrolledStudents();
					System.out.println(COLORCONSTANT.TEXT_GREEN);
					System.out.print("Enter studentId: ".toUpperCase());
					String studentId = sc.next();
					System.out.print("ENTER GRADE OF STUDENT " + studentId + ": ");
					float newGrade = sc.nextFloat();
					System.out.print("Enter courseId: ".toUpperCase());
					String courseId = sc.next();
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					professorService.addGrade(UserService.currentUsedId, newGrade, studentId, courseId);
					break;
	
				case 3:
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					ArrayList<Course> listOfCourses = CourseDaoOperation.getAllCourses();
					System.out.println(COLORCONSTANT.TEXT_BLACK);
					System.out.println("COURSE ID CREDITS DURATION PROFESSOR ID COURSE NAME");
					for (Course course : listOfCourses) {
						System.out.println(course.getCourseId()+"     "+course.getCredits()+"       "+course.getDuration()+"     "+course.getProfessorId()+"      "+course.getName());
					}
					System.out.println(COLORCONSTANT.TEXT_GREEN);
					System.out.print("Enter the courseId: ".toUpperCase());
					String cId = sc.next();
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					if(professorService.addCourse(UserService.currentUsedId, cId)) {
						System.out.println(COLORCONSTANT.TEXT_BLACK);
						System.out.println("COURSE IS ADDED");
					}
					else {
						System.out.println(COLORCONSTANT.TEXT_RED);
						System.out.println("COURSE NOT ADDED");
					}
					break;
	
				case 4:
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					HashMap<String, String> res=professorService.viewCourse(UserService.currentUsedId);
					System.out.println(COLORCONSTANT.TEXT_BLACK);
					System.out.println("Course List: ");
					System.out.println("Course Id Course Name".toUpperCase());
					res.forEach((id,name)->{
						System.out.println(id+"       "+name);
					});
					break;
				case 5:
					System.out.println(COLORCONSTANT.TEXT_GREEN);
					System.out.print("Enter your old password: ".toUpperCase());
					String password=sc.next();
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					boolean val1=user.checkPasswordforEmail(password);
					if(val1) {
						while(true) {
							System.out.println(COLORCONSTANT.TEXT_CYAN);
							System.out.println("Enter your choice:\n"+"1.Type new password \n2. Exit".toUpperCase());
							int choice = sc.nextInt();
							
							if(choice == 2) break;
							else if(choice != 1) {
								System.out.println(COLORCONSTANT.TEXT_RED);
								System.out.println("Invalid Choice".toUpperCase());
							}
							else {
								String pass1, pass2; 
								System.out.println(COLORCONSTANT.TEXT_GREEN);
								System.out.print("Type New Password: ".toUpperCase());
								pass1 = sc.next();
								System.out.print("Re-Enter New Password: ".toUpperCase());
								pass2 = sc.next();
								if(pass1.equals(pass2)) {
									System.out.println(COLORCONSTANT.TEXT_YELLOW);
									user.createNewPassword(pass1,UserService.currentUsedId);
									System.out.println(COLORCONSTANT.TEXT_BLACK);
									System.out.println("Passoword changed. Login!".toUpperCase());
									break;
								}
								else System.out.println("Passowrd Mismatch. Try Again!".toUpperCase());
							}
						}
					}
				 else {
					 System.out.println(COLORCONSTANT.TEXT_RED);
					System.out.println("Invalid credentials".toUpperCase());
				}
				break;
			default:
				System.out.println(COLORCONSTANT.TEXT_RED);
				System.out.println("Enter valid choice".toUpperCase());

			}
		}
	}

	public void viewEndrolledStudents() {
		HashMap<String, ArrayList<Pair>>list=professorService.viewEnrolledStudents(UserService.currentUsedId);
		System.out.println(COLORCONSTANT.TEXT_BLACK);
		list.forEach((key, value)->{
			System.out.println("CourseId: "+key);
			System.out.println();
			value.forEach((student)->{
				System.out.println("StudentName: "+student.getLeft()+" StudentId: "+student.getRight());
				
			});
			System.out.println("--------------------------------------");
		});
	}
}
