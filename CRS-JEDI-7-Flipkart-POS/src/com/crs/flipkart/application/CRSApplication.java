/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.DBConnection;
import com.crs.flipkart.utils.Utils.UserType;

//import 
/**
 * @author SAVAN
 *
 */
public class CRSApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CRSProfessorMenu professor=new CRSProfessorMenu();
		DBConnection.setup(AdminDaoOperation.SCHEMA);
		//Menu change 1.Register Student 2. login 3. update password 4.exit
		//role => if 1 is choosen => student
		//role => if login => admin@mail.com admin =>admin
		//addprofessor=> professor
		while(true) {
			System.out.println("__________________________________________________________");
			System.out.println("Select choice\n"+"1. Register as a Student\n"+"2. Login\n"+"3. Forget Password\n4. Exit");
			Scanner sc=new Scanner(System.in);
			int ch=sc.nextInt();
			String email,password;
			UserInterface user=new UserService();

			switch(ch) {
				case 1: System.out.println("Registering student");
				     break;
				case 2: System.out.println("Enter your email:\n");
						email=sc.next();
						System.out.println("Enter your password\n");
						password=sc.next();
						UserType val=user.authenticate(email,password);
						switch(val) {
							case Student:
								System.out.println("Student Menu here");
								break;
							case Professor:
								professor.homePage();
								break;
							case Admin:
								System.out.println("Admin Menu here");
								break;
							default:
								System.out.println("Login Unsuccessful, please try again!");
						}
					break;
				case 3: System.out.println("Enter your email:\n");
						email=sc.next();
						System.out.println("Enter your registered phone number to verify!");
						long phoneNo = sc.nextLong();
						String userId=user.forgotPassword(email,phoneNo);
						if(!(userId).equals("0")){
							while(true) {
								System.out.println("Enter your choice:\n"+"1. Change Password\n2. Exit");
								int choice = sc.nextInt();
								
								if(choice == 2) break;
								else if(choice != 1) 
									System.out.println("Invalid Choice");
								else {
									String pass1, pass2; 
									System.out.println("Type New Password!");
									pass1 = sc.next();
									System.out.println("Re-Enter New Password!");
									pass2 = sc.next();
									if(pass1.equals(pass2)) {
										user.createNewPassword(pass1,userId);
										System.out.println("Passowrd changed. Login!");
										break;
									}
									else System.out.println("Passowrd Mismatch. Try Again!");
								}
							}
						}
						else {
							System.out.println("Invalid Credentials!");
						}
						break;
				case 4: System.exit(0);
				default: System.out.println("Invalid choice");
			}
		}

	}

}
