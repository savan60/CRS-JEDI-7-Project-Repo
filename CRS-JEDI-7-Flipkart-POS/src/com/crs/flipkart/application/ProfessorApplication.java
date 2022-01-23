package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.UserService;
import com.crs.flipkart.utils.Utils.UserType;

public class ProfessorApplication {
	
	Scanner sc=new Scanner(System.in);
	UserService user=new UserService();
	
	public void entry() {
		
		while(true) {
			
			System.out.println("Enter your choice:\n"+"1. Login\n2. Forget password\n3. Return to previous options");
			
			int ch=sc.nextInt();
			if(ch==3) {
				
				break;
			}
			String email,password;
		
			switch(ch) {
			
				case 1: System.out.println("Enter your email:\n");
						email=sc.next();
						System.out.println("Enter your password\n");
						password=sc.next();
						System.out.println(email+" "+password );
						boolean val=user.authenticate(UserType.Professor,email,password);
						
						if(val) {
							System.out.println("Login Successful");
							homePage();
						}
						else {
							System.out.println("Login Unsuccessful, please try again!");
						}
						break;
						
				case 2: 
					break;
				
				
				default: System.out.println("Enter valid choice");
				
			}
		}
	}
	
	private void homePage() {
		
		System.out.println("Welcome to Professor portal!");
		
		while(true) {
			
			System.out.println("Please select operation to perform:\n1.View Enrolled Students\n2. Assign grades\n3. Logout");
			int ch=sc.nextInt();
			
			if(ch==3) {
				System.out.println("Thank you");
				break;
			}
			
			switch(ch) {
			
				case 1: //call view enrolled students
					break;
				case 2: //call assign grades
					break;
					
				default: System.out.println("Enter valid choice");
				
			}
		}
	}
	
}
