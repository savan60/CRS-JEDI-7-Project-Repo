package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.utils.Utils.UserType;

public class CRSProfessorMenu {
	
	Scanner sc=new Scanner(System.in);
	UserService user=new UserService();
	
	public void homePage() {

		System.out.println("Welcome to Professor portal!");
		
		ProfessorService professorService=new ProfessorService();
		while(true) {
			System.out.println("__________________________________________________________");

			System.out.println("Please select operation to perform:\n1. View Enrolled Students\n2. Assign grades\n3. Update password\n4. Logout");
			//update password will be here
			int ch=sc.nextInt();
			
			if(ch==4) {
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
					professorService.addGrade(UserService.currentUsedId,newGrade,studentId);
					
					break;
				case 3:
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
