/**
 * 
 */
package com.crs.flipkart.application;


import java.util.ArrayList;

import java.util.Scanner;
import com.crs.flipkart.bean.Card;
import com.crs.flipkart.business.GradeCardInterface;
import com.crs.flipkart.business.GradeCardService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.constant.COLORCONSTANT;
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

		System.out.println(COLORCONSTANT.bold);
		System.out.println(COLORCONSTANT.TEXT_PURPLE);
		System.out.println("_____________________________________________________________________________________________________________________________________");
		System.out.println();
		System.out.println("#----------------------------------------------WELCOME TO STUDENT MENU---------------------------------------------------#");

		System.out.println("_____________________________________________________________________________________________________________________________________");

		while (true) {
						System.out.println(COLORCONSTANT.TEXT_CYAN);

			System.out.print("1. Semester Registration\n".toUpperCase());
			System.out.print("2. Add Course\n".toUpperCase());
			System.out.print("3. Drop Course\n".toUpperCase());
			System.out.print("4. View Course\n".toUpperCase());
			System.out.print("5. View Registered Courses\n".toUpperCase());
			System.out.print("6. View Grade Card\n".toUpperCase());
			System.out.print("7. Make Payment\n".toUpperCase());
			System.out.print("8. Update Password\n".toUpperCase());
			System.out.print("9. Log Out\n".toUpperCase());

			System.out.println(COLORCONSTANT.TEXT_GREEN);
			System.out.print("Enter User Input: ");

			int choice = sc.nextInt();
			int sem;
			String courseId;
			System.out.println(COLORCONSTANT.TEXT_YELLOW);
			student.getSemester(UserService.currentUsedId);
			switch (choice) {

			case 1:
				System.out.println(COLORCONSTANT.TEXT_GREEN);
				System.out.print("Enter the Semester:");
				sem = sc.nextInt();
				student.setSemester(UserService.currentUsedId, sem);
				boolean val = student.semesterRegistration(sem);
				if (val) {
					System.out.println(COLORCONSTANT.TEXT_YELLOW);
					student.viewCatalogue(sem);
					System.out.println(COLORCONSTANT.TEXT_GREEN);
					int choosen = 0;
					while (choosen != 6) {
						System.out.println(COLORCONSTANT.TEXT_GREEN);
						System.out.println("\nCourse Number " + (choosen + 1) + ": ");
						System.out.print("Enter the course id:");
						courseId = sc.next();
						System.out.println(COLORCONSTANT.TEXT_YELLOW);
						boolean res = student.addCourse(courseId, sem);
						if (res) {
							choosen++;
						}
					}
				}
				break;
			case 2:
				student.viewCatalogue(StudentService.current_semester);
				System.out.println(COLORCONSTANT.TEXT_GREEN);
				System.out.print("\nEnter the course id:");
				courseId = sc.next();
				student.addCourse(courseId, StudentService.current_semester);
				break;
			case 3:
				System.out.println(COLORCONSTANT.TEXT_GREEN);
				System.out.print("Enter the course id for course you want to drop:");
				courseId = sc.next();
				student.dropCourse(UserService.currentUsedId, courseId);
				break;
			case 4:
				System.out.println(COLORCONSTANT.TEXT_YELLOW);
				student.viewCatalogue(StudentService.current_semester);
				break;
			case 5:
				System.out.println(COLORCONSTANT.TEXT_YELLOW);
				student.viewRegisteredCourses(StudentService.current_semester);
				break;
			case 6:
				System.out.println(COLORCONSTANT.TEXT_YELLOW);
				grade.viewGradeCard(UserService.currentUsedId, StudentService.current_semester);
				break;
			case 7:
				System.out.println(COLORCONSTANT.TEXT_YELLOW);
				boolean res=payment.checkForPayment(UserService.currentUsedId);
				if(res) {
					System.out.println(COLORCONSTANT.TEXT_GREEN);
					makePayment();
				}
				else {
					System.out.println(COLORCONSTANT.TEXT_RED);
					System.out.println("You don't have any pending payments");
				}
				break;
			case 8:
				System.out.println(COLORCONSTANT.TEXT_GREEN);
				System.out.print("Enter your old password: ".toUpperCase());
				String password = sc.next();
				System.out.println(COLORCONSTANT.TEXT_YELLOW);
				boolean val1 = user.checkPasswordforEmail(password);
				if (val1) {
					while (true) {
						System.out.println(COLORCONSTANT.TEXT_CYAN);
						System.out.println("Enter your choice:\n" + "1.Type new password \n2. Exit".toUpperCase());
						int ch = sc.nextInt();

						if (ch == 2)
							break;
						else if (ch != 1) {
							System.out.println(COLORCONSTANT.TEXT_RED);
							System.out.println("Invalid Choice");
						}
						else {
							String pass1, pass2;
							System.out.println(COLORCONSTANT.TEXT_GREEN);
//							 System.out.println("Type New Password!");
							pass1 = sc.next();
							System.out.print("Re-Enter New Password: ");
							pass2 = sc.next();
							if (pass1.equals(pass2)) {
								System.out.println(COLORCONSTANT.TEXT_YELLOW);
								user.createNewPassword(pass1, UserService.currentUsedId);
								System.out.println(COLORCONSTANT.TEXT_BLACK);
								System.out.println("Passowrd changed. Login!");
								break;
							} else {
								System.out.println(COLORCONSTANT.TEXT_RED);
								System.out.println("Passowrd Mismatch. Try Again!");
							}
						}
					}
				} else {
					System.out.println(COLORCONSTANT.TEXT_RED);
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
		System.out.println(COLORCONSTANT.TEXT_CYAN);
		System.out.println("Choose type of the payment: ".toUpperCase());
		System.out.println("1. Credit Card/Debit Card".toUpperCase());
		System.out.println("2. Netbanking".toUpperCase());
		System.out.println();
		System.out.println(COLORCONSTANT.TEXT_GREEN);
		System.out.print("ENTER CHOICE: ");
		System.out.println();
		int type = sc.nextInt();

		String studentId = UserService.currentUsedId;

		switch (type) {
		case 1:

			System.out.print("Enter cardNumber: ".toUpperCase());
			String cardNumber = sc.next();
			System.out.println(COLORCONSTANT.TEXT_CYAN);
			System.out.println("Enter card type\n" + "  press 1 for DEBIT card\n" + "  press 2 for CREDIT card\n".toUpperCase());
			System.out.println(COLORCONSTANT.TEXT_GREEN);
			int choice = sc.nextInt();
			CardType cardType = (choice == 1 ? CardType.DEBIT : CardType.CREDIT);

			System.out.print("Enter Expiry month(mm): ");
			int month = sc.nextInt();

			System.out.print("Enter Expiry year(yyyy): ");
			int year = sc.nextInt();

			System.out.print("Enter cvv(xxx): ");
			int cvv = sc.nextInt();

			System.out.print("Enter bank name: ");
			String bankName = sc.next();
			
			Card card = new Card(cardNumber, cardType, month, year, bankName);

//			CardDaoInterface cardDaoInterface = new CardDaoOperation();
//			cardDaoInterface.addCard(card);
			System.out.println(COLORCONSTANT.TEXT_YELLOW);
			if(payment.makePaymentByCard(card)) {
				System.out.println(COLORCONSTANT.TEXT_BLACK);
				System.out.println("Payment Successful");
			}
			else {
				System.out.println(COLORCONSTANT.TEXT_RED);
				System.out.println("Payment Failed. Try again!");
			}
			break;
		case 2:
			if(payment.makePaymentByNetBanking()) {
				System.out.println(COLORCONSTANT.TEXT_BLACK);
				System.out.println("Payment Successful");
			}
			else {
				System.out.println(COLORCONSTANT.TEXT_RED);
				System.out.println("Payment Failed. Try again!");
			}
			break;
		default:
			System.out.println(COLORCONSTANT.TEXT_RED);
			System.out.println("Choose proper payment option.");
		}
	}
}
