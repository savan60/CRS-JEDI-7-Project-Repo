package com.crs.flipkart.application;

import java.util.Scanner;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.CourseInterface;
import com.crs.flipkart.business.CourseService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.constant.COLORCONSTANT;
import com.crs.flipkart.utils.Utils;

public class CRSAdminMenu {
	Scanner sc = new Scanner(System.in);
	UserInterface user = new UserService();

	public void homePage() {
		
		System.out.println(COLORCONSTANT.TEXT_BLACK);
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
					"Please select operation to perform:\n\n1. Add Professor\n2. Add Course\n3. Remove Course\n4. Generate Grade Card \n5. Approve Students\n6. Semester Fees\n7. Logout\n".toUpperCase());
			// update password will be here
			System.out.print(COLORCONSTANT.TEXT_GREEN);
			System.out.print("Enter Choice: ".toUpperCase());
			int ch = sc.nextInt();
			System.out.println();

			if (ch == 7) {
				System.out.print(COLORCONSTANT.TEXT_BLACK);
				System.out.println("Thank you!".toUpperCase());
				break;
			}
			switch (ch) {
				case 1:
					System.out.print(COLORCONSTANT.TEXT_GREEN);
					
					System.out.print("Enter the emailid: ".toUpperCase());
					String email = sc.next();

					System.out.print("Enter the phoneNumber: ".toUpperCase());
					String phoneNumber = sc.next();

					System.out.print("Enter the address: ".toUpperCase());
					String address = sc.next();

					System.out.print("Enter the password: ".toUpperCase());
					String pass = sc.next();

					System.out.print("Enter Department: ".toUpperCase());
					String department = sc.next();

					System.out.print("Enter Position: ".toUpperCase());
					String position = sc.next();
					
					int res = adminService.addProfessor(email, phoneNumber, address, pass, department, position);
					
					if (res == 1) {
						System.out.println(COLORCONSTANT.TEXT_BLACK);
						System.out.println("Professor added!");
					} else if (res==2) {
						System.out.println(COLORCONSTANT.TEXT_RED);
						System.out.println("Professor with this email already exists!");
					} else{
						System.out.println(COLORCONSTANT.TEXT_RED);
						System.out.println("Please try again!");
					}
					break;

				case 2: // call add course
					System.out.print(COLORCONSTANT.TEXT_GREEN);
					
					String cId = Utils.generateUniqueId().substring(0,3) + Utils.generateUniqueId().substring(10,13);

					System.out.print("Enter the course name: ".toUpperCase());
					String cname = sc.next();

					System.out.print("Enter the duration: ".toUpperCase());
					float cdur = sc.nextFloat();

					System.out.print("Enter the credits: ".toUpperCase());
					float credits = sc.nextFloat();
					System.out.println(COLORCONSTANT.TEXT_BLACK);
					if (courseService.addCourse(cId, cname, cdur, credits)) {
						System.out.print(COLORCONSTANT.TEXT_BLACK);
						System.out.println("Course added!");
					} else {
						System.out.print(COLORCONSTANT.TEXT_RED);
						System.out.println("Please try again!");
					}
					break;

				case 3:
					System.out.print(COLORCONSTANT.TEXT_GREEN);
					
					System.out.print("Enter Course id to remove: ".toUpperCase());
					String rid = sc.next();

					if (courseService.deleteCourse(rid)) {
						System.out.println(COLORCONSTANT.TEXT_BLACK);
						System.out.println("Course removed!");
					} else {
						System.out.println(COLORCONSTANT.TEXT_RED);
						System.out.println("Course id does not exists, or please try again!");
					}
					break;

				case 4:
					System.out.print(COLORCONSTANT.TEXT_GREEN);
					System.out.print("Enter semester: ".toUpperCase());
					int sem = sc.nextInt();
					adminService.genReportCard(sem);
					System.out.println(COLORCONSTANT.TEXT_BLACK);
					System.out.print("Grade Cards Generated!");
					break;

				case 5:
					System.out.print(COLORCONSTANT.TEXT_GREEN);
					System.out.println("Select your choice: \n1. Approve all students\n2. Approve Students one by one.");
					int choice = sc.nextInt();
					switch (choice) {
						case 1:
							adminService.approveAllStudents();
							break;

						case 2:
							adminService.approveStudentsOneByOne();
							break;

						default:
							System.out.println(COLORCONSTANT.TEXT_RED);
							System.out.println("Invalid Choice");
					}

					break;

				case 6:
					System.out.print(COLORCONSTANT.TEXT_GREEN);
					System.out.print("Enter fee amount: ".toUpperCase());
					int amount = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter payment message: ".toUpperCase());
					String message = sc.nextLine();
					paymentService.askForPayment(amount, message);
					System.out.println(COLORCONSTANT.TEXT_BLACK);
					System.out.println("Payment added to Students!".toUpperCase());
					break;
				default:
					System.out.println(COLORCONSTANT.TEXT_RED);
					System.out.println("Enter valid choice".toUpperCase());

			}
		}
	}
}
