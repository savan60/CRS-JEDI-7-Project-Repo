/**
 * 
 */
package com.crs.flipkart.application;

import java.lang.System.Logger;
import java.util.Scanner;

import com.crs.flipkart.bean.Card;
import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.business.GradeCardInterface;
import com.crs.flipkart.business.GradeCardService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.dao.CardDaoInterface;
import com.crs.flipkart.dao.CardDaoOperation;
import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;
import com.crs.flipkart.exceptions.StudentNotFound;
import com.crs.flipkart.utils.Utils.CardType;

/**
 * @author SAVAN
 *
 */
public class CRSStudentMenu {
	static Scanner sc = new Scanner(System.in);
	StudentInterface student = new StudentService();
	GradeCardInterface grade = new GradeCardService();
	PaymentInterface payment = new PaymentService();
	UserInterface user = new UserService();

	public void homepage() {
////		public static final String ANSI_RESET = "\u001B[0m";
//		public static final String ANSI_BLACK = "\u001B[30m";
//		public static final String ANSI_RED = "\u001B[31m";
//		public static final String ANSI_GREEN = "\u001B[32m";
//		public static final String ANSI_YELLOW = "\u001B[33m";
//		public static final String ANSI_BLUE = "\u001B[34m";
//		public static final String ANSI_PURPLE = "\u001B[35m";
//		public static final String ANSI_CYAN = "\u001B[36m";
//		public static final String ANSI_WHITE = "\u001B[37m";
		while (true) {

			System.out
					.println("\\033[1m#------------------------Welcome to Course Registration System------------------------#\\033[0m");

			System.out.println("***********************************************************************************");

			System.out.println("1. Semester Registration");
			System.out.println("2. Add Course");
			System.out.println("3. Drop Course");
			System.out.println("4. View Course");
			System.out.println("5. View Registered Courses");
			System.out.println("6. View Grade Card");
			System.out.println("7. Make Payment");
			System.out.println("8. Update Password");
			System.out.println("9. Log Out");

			System.out.println("*********************************************************************************");

			System.out.print("Enter User Input: ");

			int choice = sc.nextInt();
			int sem;
			String courseId;
			student.getSemester(UserService.currentUsedId);
			switch (choice) {

			case 1:
				System.out.println("Enter the Semester:");
				sem = sc.nextInt();
				System.out.println("userid: " + UserService.currentUsedId);
				student.setSemester(UserService.currentUsedId, sem);
				boolean val = student.semesterRegistration(sem);
				if (val) {
					student.viewCatalogue(sem);
					int choosen = 0;
					while (choosen != 6) {
						System.out.println("Course Number " + (choosen + 1) + ": ");
						System.out.println("Enter the course id:");
						courseId = sc.next();
						boolean res = student.addCourse(courseId, sem);
						if (res) {
							choosen++;
						}
					}
				}
				break;
			case 2:
				student.viewCatalogue(StudentService.current_semester);
				System.out.println("Enter the course id:");
				courseId = sc.next();
				// 1=>semester
				System.out.println("Semester is " + StudentService.current_semester);
				student.addCourse(courseId, StudentService.current_semester);
				break;
			case 3:
				System.out.println("Enter the course id for course you want to drop:");
				courseId = sc.next();
				student.dropCourse(UserService.currentUsedId, courseId);
				break;
			case 4:

				student.viewCatalogue(StudentService.current_semester);
				break;
			case 5:
				student.viewRegisteredCourses(StudentService.current_semester);
				break;
			case 6:
				grade.viewGradeCard(UserService.currentUsedId, StudentService.current_semester);
				break;
			case 7:
				boolean res=payment.checkForPayment(UserService.currentUsedId);
				if(res) {
					makePayment();
				}
				else {
					System.out.println("You don't have any pending payments");
				}
				break;
			case 8:
				System.out.println("Enter your old password\n");
				String password = sc.next();
				boolean val1 = user.checkPasswordforEmail(password);
				if (val1) {
					while (true) {
						System.out.println("Enter your choice:\n" + "1.Type new password \n2. Exit");
						int ch = sc.nextInt();

						if (ch == 2)
							break;
						else if (ch != 1)
							System.out.println("Invalid Choice");
						else {
							String pass1, pass2;
							// System.out.println("Type New Password!");
							pass1 = sc.next();
							System.out.println("Re-Enter New Password!");
							pass2 = sc.next();
							if (pass1.equals(pass2)) {
								user.createNewPassword(pass1, UserService.currentUsedId);
								System.out.println("Passowrd changed. Login!");
								break;
							} else
								System.out.println("Passowrd Mismatch. Try Again!");
						}
					}
				} else {
					System.out.println("Invalid credentials");
				}
				break;
			case 9:
				return;
			default:
				System.out.println("Invalid Input !");
			}
		}
	}

	private void makePayment() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose type of the payment: ");
		System.out.println("1. Credit Card/Debit Card");
		System.out.println("2. Netbanking");
		int type = sc.nextInt();

		String studentId = UserService.currentUsedId;

		switch (type) {
		case 1:

			System.out.println("Enter cardNumber:");
			String cardNumber = sc.next();

			System.out.println("Enter card type\n" + "  press 1 for DEBIT card\n" + "  press 2 for CREDIT card\n");
			int choice = sc.nextInt();
			CardType cardType = (choice == 1 ? CardType.DEBIT : CardType.CREDIT);

			System.out.println("Enter Expiry month(mm): ");
			int month = sc.nextInt();

			System.out.println("Enter Expiry year(yyyy): ");
			int year = sc.nextInt();

			System.out.println("Enter cvv(xxx): ");
			int cvv = sc.nextInt();

			System.out.println("Enter bank name: ");
			String bankName = sc.next();

			Card card = new Card(cardNumber, cardType, month, year, bankName);

//			CardDaoInterface cardDaoInterface = new CardDaoOperation();
//			cardDaoInterface.addCard(card);
			if(payment.makePaymentByCard(card)) {
				System.out.println("Payment Successful");
			}
			else {
				System.out.println("Payment Failed. Try again!");
			}
			break;
		case 2:
			if(payment.makePaymentByNetBanking()) {
				System.out.println("Payment Successful");
			}
			else {
				System.out.println("Payment Failed. Try again!");
			}
			break;
		default:
			System.out.println("Choose proper payment option.");
		}
	}
}
