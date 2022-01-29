/**
 * 
 */
package com.crs.flipkart.application;

import java.lang.System.Logger;
import java.util.Scanner;

import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.business.GradeCardInterface;
import com.crs.flipkart.business.GradeCardService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;

/**
 * @author SAVAN
 *
 */
public class CRSStudentMenu {
	static Scanner sc = new Scanner(System.in);
	public void homepage() {
		StudentInterface student=new StudentService();
		GradeCardInterface grade=new GradeCardService();
		PaymentInterface payment=new PaymentService();
		UserInterface user = new UserService();
		
		while (true) {

			System.out.println("#------------------------Welcome to Course Registration System------------------------#");

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
			//getSemster is giving error
//			student.getSemester(UserService.currentUsedId);
			StudentService.current_semester=1;//remove this statement after getsemester is fixed
			//getSemster() => StudentService
			switch (choice) {

			case 1:
				System.out.println("Enter the Semester:");
				sem=sc.nextInt();
				System.out.println("userid: "+UserService.currentUsedId);
				student.setSemester(UserService.currentUsedId,sem);
				boolean val=student.semesterRegistration(sem);
				if(val) {
					student.viewCatalogue(sem);
					int choosen=0;
					while(choosen!=6) {
						System.out.println("Course Number "+(choosen+1)+": ");
						System.out.println("Enter the course id:");
						courseId=sc.next();
						boolean res=student.addCourse(courseId, sem);
						if(res) {
							choosen++;
						}
					}
				}
				break;
			case 2:
				//-----------------------------------------------------------------------
				//Remaining: Show list of course
				//Test that total courses selection doesn't excedd 6
				//1=> semester
				student.viewCatalogue(StudentService.current_semester);
				System.out.println("Enter the course id:");
				courseId=sc.next();
				//1=>semester
				System.out.println("Semester is "+StudentService.current_semester);
				student.addCourse(courseId, StudentService.current_semester);
				break;
			case 3:
//				dropCourse(studentId); // change 2 -> fixed the flow for drop course
				System.out.println("Enter the course id for course you want to drop:");
				courseId=sc.next();
				//1=>semester
				student.dropCourse(UserService.currentUsedId,courseId); 
				break;
			case 4:
				//sem take from student table
				
				student.viewCatalogue(StudentService.current_semester);
//				viewAvailableCourse(studentId);
				break;
			case 5:
				//1=> semester
				student.viewRegisteredCourses(StudentService.current_semester);
//				viewRegisteredCourse(studentId);
				break;
			case 6:
				//1=>semester
				grade.viewGradeCard(UserService.currentUsedId, StudentService.current_semester);
//				viewGradeCard(studentId);
				break;
			case 7:
//				makePayment(studentId);
				payment.makePayment();
				break;
			case 8:
				System.out.println("Enter your old password\n");
				String password=sc.next();
				boolean val1=user.checkPasswordforEmail(password);
				if(val1) {
					while(true) {
						System.out.println("Enter your choice:\n"+"1.Type new password \n2. Exit");
						int ch = sc.nextInt();
						
						if(ch == 2) break;
						else if(ch != 1) 
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
			case 9:
				return;
			default:
				System.out.println("Invalid Input !");
			}
		}
	}
}
