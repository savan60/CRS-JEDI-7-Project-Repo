package com.crs.flipkart.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.CourseInterface;
import com.crs.flipkart.business.CourseService;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.utils.Utils.UserType;

public class CRSAdminMenu {
	Scanner sc=new Scanner(System.in);
	UserInterface user=new UserService();
	
	public void homePage() {

		System.out.println("Welcome to Admin portal!");
		
		AdminInterface adminService=new AdminService();
		CourseInterface courseService=new CourseService();
		while(true) {
			System.out.println("__________________________________________________________");
			
			System.out.println("Please select operation to perform:\n1. Add Professor\n2. Add Course\n3. Remove Course\n4. Generate Grade Card \n5. Update password\n6. Approve Students\n7. Logout");
			//update password will be here
			int ch=sc.nextInt();
			
			if(ch==7) {
				System.out.println("Thank you");
				break;
			}

			switch(ch) {
				case 1: 
//					System.out.println("Enter the user id:");
//					String id=sc.next();
//					
//					System.out.println("Enter the emailid:");
//					String email=sc.next();
//					
//					System.out.println("Enter the phoneNumber:");
//					String num=sc.next();
//					
//					System.out.println("Enter the address:");
//					String add=sc.next();
//					
//					System.out.println("Enter the password:");
//					String pass=sc.next();
//					
//					System.out.println("Enter Department:");
//					String dept=sc.next();
//					
//					System.out.println("Enter Position:");
//					String pos=sc.next();
					
					if(adminService.addProfessor()) {
						System.out.println("Professor added");
					}
					else 
						System.out.println("User id already exists! or Please try again!");
					
					break;
					
				case 2: //call add course
					System.out.println("Enter the course id:");
					String cid=sc.next();
					
					System.out.println("Enter the course name:");
					String cname=sc.next();
					
					System.out.println("Enter the duration:");
					float cdur=sc.nextFloat();
					
					System.out.println("Enter the credits:");
					float credits=sc.nextFloat();
					
					if(courseService.addCourse(cid,cname,cdur,credits)) {
						System.out.println("Course added");
					}
					else 
						System.out.println("Course id already exists, please try again!");
					
					break;
					
				case 3:
					System.out.println("Enter Course id to remove: ");
					String rid=sc.next();
					
					if(courseService.deleteCourse(rid)) {
						System.out.println("Course removed");
					}
					else 
						System.out.println("Course id does not exists, please try again!");
					break;
					
				case 4:
					System.out.println("Enter the semester");
					int sem=sc.nextInt();
					adminService.genReportCard(sem);
					break;
					
				case 5:
					System.out.println("Enter your old password\n");
					String password=sc.next();
					boolean val1=user.checkPasswordforEmail(password);
					if(val1) {
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
					
				case 6:	System.out.println("Select your choice: \n1. Approve all students\n2. Approve Students one by one.");
						int choice= sc.nextInt();
						
						switch(choice) {
						
						case 1: adminService.approveAllStudents();
								break;
						
						case 2: adminService.approveStudentsOneByOne();
								break;
						
						default: System.out.println("Invalid Choice");
						}
						
					break;
				
				default: System.out.println("Enter valid choice");
				
			}
		}
	}
}
