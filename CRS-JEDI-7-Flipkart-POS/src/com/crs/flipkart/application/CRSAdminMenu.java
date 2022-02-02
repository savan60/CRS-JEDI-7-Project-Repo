package com.crs.flipkart.application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.CourseInterface;
import com.crs.flipkart.business.CourseService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.constant.COLORCONSTANT;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.UserType;

public class CRSAdminMenu {
	Scanner sc = new Scanner(System.in);
	UserInterface user = new UserService();

	public void homePage() {

		System.out.println("Welcome to Admin portal!");

		AdminInterface adminService = new AdminService();
		CourseInterface courseService = new CourseService();
		PaymentInterface paymentService = new PaymentService();

		while (true) {
			System.out.println(COLORCONSTANT.TEXT_PURPLE);
			System.out.println("_____________________________________________________________________________________________________________________________________");
			System.out.println();
			System.out.println("#--------------------------------------------WELCOME TO ADMIN MENU-------------------------------------------------------------------#");

			System.out.println("_____________________________________________________________________________________________________________________________________");
			System.out.println(COLORCONSTANT.TEXT_CYAN);
			System.out.println(
					"Please select operation to perform:\n\n1. Add Professor\n2. Add Course\n3. Remove Course\n4. Generate Grade Card \n5. Update password\n6. Approve Students\n7. Semester Fees\n8. Logout\n".toUpperCase());
			// update password will be here
			System.out.println(COLORCONSTANT.TEXT_GREEN);
			System.out.print("Enter Choice: ".toUpperCase());
			int ch = sc.nextInt();

			if (ch == 8) {
				System.out.println("Thank you");
				break;
			}
			switch (ch) {
				case 1:
					System.out.print("Enter the emailid:");
					String email = sc.next();

					System.out.print("Enter the phoneNumber:");
					String phoneNumber = sc.next();

					System.out.print("Enter the address:");
					String address = sc.next();

					System.out.print("Enter the password:");
					String pass = sc.next();

					System.out.print("Enter Department:");
					String department = sc.next();

					System.out.print("Enter Position:");
					String position = sc.next();

					if (adminService.addProfessor(email, phoneNumber, address, pass, department, position)) {
						System.out.println(COLORCONSTANT.TEXT_BLACK);
						System.out.println("Professor added");
					} else {
						System.out.println(COLORCONSTANT.TEXT_RED);
						System.out.println("User id already exists! or Please try again!");
					}
					break;

				case 2: // call add course
					String cId = Utils.generateUniqueId().substring(0,3) + Utils.generateUniqueId().substring(10,13);

					System.out.print("Enter the course name:");
					String cname = sc.next();

					System.out.print("Enter the duration:");
					float cdur = sc.nextFloat();

					System.out.print("Enter the credits:");
					float credits = sc.nextFloat();
					System.out.println(COLORCONSTANT.TEXT_BLACK);
					if (courseService.addCourse(cId, cname, cdur, credits)) {
						System.out.println(COLORCONSTANT.TEXT_BLACK);
						System.out.println("Course added");
					} else {
						System.out.println(COLORCONSTANT.TEXT_RED);
						System.out.println("Course id already exists, please try again!");
					}
					break;

				case 3:
					System.out.println("Enter Course id to remove: ");
					String rid = sc.next();

					if (courseService.deleteCourse(rid)) {
						System.out.println("Course removed");
					} else
						System.out.println("Course id does not exists, please try again!");
					break;

				case 4:
					System.out.println("Enter the semester");
					int sem = sc.nextInt();
					adminService.genReportCard(sem);
					break;

				case 5:
					System.out.println("Enter your old password\n");
					String password = sc.next();
					boolean val1 = user.checkPasswordforEmail(password);
					if (val1) {
						while (true) {
							System.out.println("Enter your choice:\n" + "1.Type new password \n2. Exit");
							int choice = sc.nextInt();

							if (choice == 2)
								break;
							else if (choice != 1)
								System.out.println("Invalid Choice");
							else {
								String pass1, pass2;
								// System.out.println("Type New Password!");
								pass1 = sc.next();
								System.out.println("Re-Enter New Password!");
								pass2 = sc.next();
								if (pass1.equals(pass2)) {
									user.createNewPassword(pass1, UserService.currentUsedId);
									System.out.println("Password changed. Login!");
									break;
								} else
									System.out.println("Password Mismatch. Try Again!");
							}
						}
					} else {
						System.out.println("Invalid credentials");
					}
					break;

				case 6:
					System.out
							.println("Select your choice: \n1. Approve all students\n2. Approve Students one by one.");
					int choice = sc.nextInt();

					switch (choice) {

						case 1:
							adminService.approveAllStudents();
							break;

						case 2:
							adminService.approveStudentsOneByOne();
							break;

						default:
							System.out.println("Invalid Choice");
					}

					break;

				case 7:
					System.out.println("Enter fee amount: ");
					int amount = sc.nextInt();
					System.out.println("Enter payment message: ");
					String message = sc.next();
					paymentService.askForPayment(amount, message);
					System.out.println("Payment added to Students");
					break;
				default:
					System.out.println("Enter valid choice");

			}
		}
	}
}
