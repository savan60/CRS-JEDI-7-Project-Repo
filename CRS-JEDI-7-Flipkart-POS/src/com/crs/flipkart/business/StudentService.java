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
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constant.COLORCONSTANT;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.exceptions.StudentNotFound;
import com.crs.flipkart.exceptions.CheckForSemesterRegistration;
import com.crs.flipkart.exceptions.AddCourseLimitExceed;
import com.crs.flipkart.exceptions.CourseNotEndrolledByStudent;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.UserType;
import com.crs.flipkart.validator.StudentValidator;
//import com.mysql.jdbc.log.Log4JLogger;

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
			current_semester=student.getSemester(id);
			return current_semester;
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
	public boolean selfRegistration(Student student) {
		StudentDaoInterface studentDaoInterface = new StudentDaoOperation();
		return studentDaoInterface.addStudent(student);
	}
	
	public void viewGradeCard() {
		
	}
	public void viewCatalogue(int sem) {
		courseInterface.viewCourses(sem);
	}
	
	public void viewRegisteredCourses(int sem) {
		//take course from student table
		registeredCourse.printRegisteredCourses(UserService.currentUsedId, sem);
	}
	
	public boolean semesterRegistration(int sem) {
		
		try {
			semesterRegistration.checkSemAndStudentIdExists(sem, UserService.currentUsedId);
				boolean res=semesterRegistration.insertSem(sem, UserService.currentUsedId);
				if(res) {
					System.out.println("Semester Registration Successful");
					return true;
				}
				else {
					System.out.println("Semseter Registration Failed, Try again!");
				}
		}
		catch(CheckForSemesterRegistration ex) {
			System.out.println(ex.getMessage());
		}
		
		return false;
	}
	
	public boolean addCourse(String courseId,int sem) {
		ArrayList<Course> courses=CourseDaoOperation.getAllCourses();
		if(!StudentValidator.isValidCourseCode(courseId, courses)) {
			System.out.println(COLORCONSTANT.TEXT_RED);
			System.out.println("Course not available");
			return false;
		}
		try {
			boolean val=registeredCourse.addCourse(courseId,UserService.currentUsedId,sem);
			if(val) {
				System.out.println(COLORCONSTANT.TEXT_BLACK);
				System.out.println("Course added successfully");
				return true;
			}
			else {
				System.out.println(COLORCONSTANT.TEXT_RED);
				System.out.println("Course is not added, Try again");
			}
			return false;
		} catch (AddCourseLimitExceed e) {
			// TODO Auto-generated catch block
			System.out.println(COLORCONSTANT.TEXT_RED);
			System.out.println("You can't add courses more than: "+e.getCourse());
		}
		return false;
	}
	
	public boolean dropCourse(String StudentId, String courseId)
	{
		try {
			boolean val=registeredCourse.dropCourse(StudentId, courseId);
			if(val) {
				System.out.println(COLORCONSTANT.TEXT_BLACK);
			System.out.println("Course dropped successfully");
			return true;
			}
			else {
			System.out.println(COLORCONSTANT.TEXT_RED);
			System.out.println("Course is not dropped, Try again");
			}
		} catch (CourseNotEndrolledByStudent e) {
			// TODO Auto-generated catch block
			System.out.println("Course is not endrolled by student: "+e.getCourseId());
		}
		return false;
	}

	
}
