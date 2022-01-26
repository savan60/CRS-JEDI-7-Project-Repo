/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.business.GradeCardInterface;
import com.crs.flipkart.business.GradeCardService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
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
		while (true) {

			System.out
					.println("#------------------------Welcome to Course Registration System------------------------#");

			System.out.println("***********************************************************************************");

			System.out.println("1. Semester Registration");
			System.out.println("2. Add Course");
			System.out.println("3. Drop Course");
			System.out.println("4. View Course");
			System.out.println("5. View Registered Courses");
			System.out.println("6. View Grade Card");
			System.out.println("7. Make Payment");
			System.out.println("8. Exit");

			System.out.println("*********************************************************************************");

			System.out.print("Enter User Input: ");

			int choice = sc.nextInt();
			int sem;
			String courseId;
			//getSemster() => StudentService
			switch (choice) {

			case 1:
				System.out.println("Enter the Semester:");
				sem=sc.nextInt();
				// updateSemester(sem);
				//currentsem also changes
				
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
				student.viewCatalogue(1);
				System.out.println("Enter the course id:");
				courseId=sc.next();
				//1=>semester
				student.addCourse(courseId, 1);
				break;
			case 3:
//				dropCourse(studentId);
				break;
			case 4:
				//sem take from student table
				
				student.viewCatalogue(1);
//				viewAvailableCourse(studentId);
				break;
			case 5:
				//1=> semester
				student.viewRegisteredCourses(1);
//				viewRegisteredCourse(studentId);
				break;
			case 6:
				//1=>semester
				grade.viewGradeCard(UserService.currentUsedId, 1);
//				viewGradeCard(studentId);
				break;
			case 7:
//				makePayment(studentId);
				payment.makePayment();
				break;
			case 8:
				return;
			default:
				System.out.println("Invalid Input !");
			}
		}
	}
}
