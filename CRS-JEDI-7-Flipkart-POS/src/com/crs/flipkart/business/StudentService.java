package com.crs.flipkart.business;
import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;
import com.crs.flipkart.dao.SemesterRegistrationDaoInterface;
import com.crs.flipkart.dao.SemesterRegistrationDaoOperation;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.CourseDaoOperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.exceptions.StudentNotFound;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.UserType;
import com.mysql.jdbc.log.Log4JLogger;

public class StudentService implements StudentInterface{
	SemesterRegistrationDaoInterface semesterRegistration=new SemesterRegistrationDaoOperation();
	RegisteredCourseDaoInterface registeredCourse=new RegisteredCourseDaoOperation();
	CourseDaoInterface courseInterface=new CourseDaoOperation();
	private static Logger logger = Logger.getLogger(StudentService.class);
	
	//static variable for semester
	public static int current_semester;
	
	public int getSemester(String id) {
		StudentDaoOperation student=new StudentDaoOperation();
		try {
			return student.getSemester(id);
		} catch (StudentNotFound e) {
			// TODO Auto-generated catch block
			logger.error("Student not found : "+ e.getId());
		}
	
		return 0;
	}
	
	public void setSemester(String id,int sem) {
		StudentDaoOperation student=new StudentDaoOperation();
		current_semester=sem;
		try {
			 student.setSemester(id,sem);
			 
		} catch (StudentNotFound e) {
			// TODO Auto-generated catch block
			logger.error("Student not found : "+ e.getId());
		}

	}
	
	// student self register his/her self
	public void selfRegistration() {
		
		
		Scanner sc = new Scanner(System.in);
		
		String studentId = Utils.generateUniqueId().substring(0,3) + Utils.generateUniqueId().substring(10,13);
		
		System.out.println("Enter name: ");
		String name = sc.next();
		
		System.out.println("Enter email address: ");
		String email = sc.next();
		
		System.out.println("Enter phonenumber: ");
		String phoneNumber = sc.next();
		
		System.out.println("Enter address: ");
		String address = sc.next();
		
		System.out.println("Enter password: ");
		String password = sc.next();
		
		Student student = new Student(studentId, email, name, phoneNumber, address, UserType.Student, password);
		
		StudentDaoInterface studentDaoInterface = new StudentDaoOperation();
		try {
			studentDaoInterface.addStudent(student);
		} catch (StudentNotFound e) {
			// TODO Auto-generated catch block
			logger.error("Student not found : " + e.getId());
		}
	}
	
	public void viewGradeCard() {
		
	}
	public void viewCatalogue(int sem) {
		courseInterface.viewCourses(sem);
	}
	
	public void viewRegisteredCourses(int sem) {
		//take course from student table
		registeredCourse.printRegisteredCourses(UserService.currentUsedId, 1);
	}
	public boolean semesterRegistration(int sem) {
		boolean val=semesterRegistration.checkSemAndStudentIdExists(sem, UserService.currentUsedId);
		if(!val) {
			boolean res=semesterRegistration.insertSem(sem, UserService.currentUsedId);
			if(res) {
				System.out.println("Semester Registration Successful");
				return true;
			}
			else {
				System.out.println("Semseter Registration Failed, Try again!");
			}
		}
		else {
			System.out.println("Registration of the semester is already done");
		}
		return false;
	}
	
	public boolean addCourse(String courseId,int sem) {
		boolean val=registeredCourse.addCourse(courseId,UserService.currentUsedId,sem);
		if(val) {
			System.out.println("Course added successfully");
			return true;
		}
		else {
			System.out.println("Course is not added, Try again");
		}
		return false;
	}
	
	public boolean dropCourse(String StudentId, String courseId)
	{
		boolean val=registeredCourse.dropCourse(courseId,StudentId);
		if(val) {
		System.out.println("Course dropped successfully");
		return true;
		}
		else {
		System.out.println("Course is not dropped, Try again");
		}
	return false;
		
	}

	
}
