
/**
 * 
 */
package com.crs.flipkart.application;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.constant.COLORCONSTANT;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.CardDaoOperation;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.GradeCardDaoOperation;
import com.crs.flipkart.dao.PaymentDaoOperation;
import com.crs.flipkart.dao.PaymentNotifierDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;
import com.crs.flipkart.dao.SemesterRegistrationDaoOperation;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.Utils;
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
		//System.out.println(COLORCONSTANT.TEXT_BLACK);
		CRSProfessorMenu professor = new CRSProfessorMenu();
		CRSStudentMenu student = new CRSStudentMenu();
		StudentInterface studentInterface = new StudentService();
		DBUtils.getConnection();
		AdminDaoOperation.createTable();
		StudentDaoOperation.createTable();
		ProfessorDaoOperation.createTable();
		CourseDaoOperation.createTable();
		UserDaoOperation.createTable();
		CardDaoOperation.createTable();
		GradeCardDaoOperation.createTable();
		PaymentDaoOperation.createTable();
		PaymentNotifierDaoOperation.createTable();
		RegisteredCourseDaoOperation.createTable();
		SemesterRegistrationDaoOperation.createTable();
		
		while (true) {
			System.out.println(COLORCONSTANT.bold);
			System.out.println(COLORCONSTANT.TEXT_PURPLE);
			System.out.println("_____________________________________________________________________________________________________________________________________");
			System.out.println();
			System.out.println("#--------------------------------------------WELCOME TO COURSE REGISTRATION SYSTEM-------------------------------------------------#");

			System.out.println("_____________________________________________________________________________________________________________________________________");
			System.out.println(COLORCONSTANT.TEXT_CYAN);
			System.out.println(
					"1. Register as a Student\n2. Login\n3. Forget Password\n4. Exit".toUpperCase());
			System.out.println(COLORCONSTANT.TEXT_GREEN);
			System.out.print("Enter Choice: ".toUpperCase());
			Scanner sc = new Scanner(System.in);
			int ch = sc.nextInt();
			String email, password, name, phoneNumber, address;

			UserInterface user = new UserService();

			switch (ch) {
			case 1:
				System.out.println(COLORCONSTANT.TEXT_GREEN);
				String studentId = Utils.generateUniqueId().substring(0, 3)
						+ Utils.generateUniqueId().substring(10, 13);

				System.out.print("Enter name: ");
				name = sc.next();

				System.out.print("Enter email address: ".toUpperCase());
				String emai = sc.next();

				System.out.print("Enter phonenumber: ".toUpperCase());
				phoneNumber = sc.next();

				System.out.print("Enter address: ".toUpperCase());
				address = sc.next();

				System.out.print("Enter password: ".toUpperCase());
				password = sc.next();

				Student stud = new Student(studentId, emai, name, phoneNumber, address, UserType.Student, password);
				System.out.println();
				if(studentInterface.selfRegistration(stud)) {
					System.out.println(COLORCONSTANT.TEXT_BLACK);
					System.out.println("Registration completed");
				}
				break;
			case 2:
				System.out.println(COLORCONSTANT.TEXT_GREEN);
				System.out.print("Enter your email: ".toUpperCase());
				email = sc.next();
				System.out.print("Enter your password: ".toUpperCase());
				password = sc.next();


				System.out.println(COLORCONSTANT.TEXT_BLACK);

				UserType val = UserType.None;
				try {
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					val = user.authenticate(email, password);
				} catch (UserNotFoundException e) {
					System.out.println(COLORCONSTANT.TEXT_RED);
					System.out.println("User Not Found: " + e.getuserCredential());
				}

				if (val != UserType.None) {

					System.out.println(COLORCONSTANT.TEXT_BLACK);

					System.out.print(COLORCONSTANT.TEXT_BLACK);
					System.out.println("User " + UserService.currentUsedId + " logged in on :" + LocalDate.now()
							+ " at " + LocalTime.now());
				}

				switch (val) {
				case Student:
					student.homepage();
					break;
				case Professor:
					professor.homePage();
					break;
				case Admin:
					CRSAdminMenu admin = new CRSAdminMenu();
					admin.homePage();
					break;

				default:
					System.out.println(COLORCONSTANT.TEXT_RED);
					System.out.println("Login Unsuccessful, please try again!");
				}
				break;
			case 3:
				System.out.println(COLORCONSTANT.TEXT_GREEN);
				System.out.print("Enter your email: ".toUpperCase());
				email = sc.next();
				System.out.print("Enter your registered phone number to verify!: ".toUpperCase());
				String phoneNo = sc.next();
				System.out.println(COLORCONSTANT.TEXT_BLACK);
				String userId = "0";
				try {
					userId = user.forgotPassword(email, phoneNo);
				} catch (UserNotFoundException e) {
					System.out.println(COLORCONSTANT.TEXT_RED);
					System.out.println(e.getMessage());
				}
				if (!(userId).equals("0")) {
					while (true) {
						System.out.println(COLORCONSTANT.TEXT_CYAN);
						System.out.println("Enter your choice:\n" + "1. Change Password\n2. Exit".toUpperCase());
						int choice = sc.nextInt();

						if (choice == 2)
							break;
						else if (choice != 1) {
							System.out.println(COLORCONSTANT.TEXT_RED);
							System.out.println("Invalid Choice");
						}
						else {
							String pass1, pass2;
							System.out.println(COLORCONSTANT.TEXT_GREEN);
							System.out.print("Type New Password: ".toUpperCase());
							pass1 = sc.next();
							System.out.print("Re-Enter New Password: ".toUpperCase());
							pass2 = sc.next();
							if (pass1.equals(pass2)) {
								if (user.createNewPassword(pass1, userId)) {
									System.out.println(COLORCONSTANT.TEXT_BLACK);
									System.out.println("Passowrd changed. Login!");
									break;
								} else {
									System.out.println(COLORCONSTANT.TEXT_RED);
									System.out.println("Failed, Try Again!");
								}
							} else {
								System.out.println(COLORCONSTANT.TEXT_RED);
								System.out.println("Passowrd Mismatch. Try Again!");
							}
						}
					}
				}
				else {
					System.out.println(COLORCONSTANT.TEXT_RED);
					System.out.println("Invalid Credentials!");
				}
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println(COLORCONSTANT.TEXT_RED);
				System.out.println("Invalid choice");
			}
		}
	}

}
