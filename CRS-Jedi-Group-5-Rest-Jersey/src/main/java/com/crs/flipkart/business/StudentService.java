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
import com.crs.flipkart.exceptions.CheckForSemesterRegistration;
import com.crs.flipkart.exceptions.AddCourseLimitExceed;
import com.crs.flipkart.exceptions.CourseNotEndrolledByStudent;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.UserType;
//import com.mysql.jdbc.log.Log4JLogger;

public class StudentService implements StudentInterface{
	SemesterRegistrationDaoInterface semesterRegistration=new SemesterRegistrationDaoOperation();
	RegisteredCourseDaoInterface registeredCourse=new RegisteredCourseDaoOperation();
	CourseDaoInterface courseInterface=new CourseDaoOperation();
	private static Logger logger = Logger.getLogger(StudentService.class);
	//static variable for semester
	public static int current_semester;
	
	private static volatile StudentService instance = null;


	/**
	 * 
	 * 
	 * create a instance of StudentService
	 *
	 * @return instance of the StudentService
	 * 
	 */
	public static StudentService getInstance()
	{
		if(instance == null)
		{
			synchronized(StudentService.class){
				instance = new StudentService();
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * Returns the semester the student with studentId id is enrolled in
	 * 
	 * @param id
	 * @return int, semester the student is enrolled in, 0 if student is not available or exception is raised
	 * 
	 *
	 * 
	 */
	public int getSemester(String id) {
		logger.info("getSemester method started");

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
		logger.info("setSemester method started");

		StudentDaoOperation student=new StudentDaoOperation();
		current_semester=sem;
		try {
			 student.setSemester(id,sem);
			 
		} catch (StudentNotFound e) {
			// TODO Auto-generated catch block
			logger.error("Student not found : "+ e.getId());
		}

	}
	
	/**
	 * 
	 * Adds a student to the database
	 * 
	 * @param student, object containing details of the student to be registered
	 * 
	 * 
	 */
	
	// student self register his/her self
	public boolean selfRegistration(Student student) {
		logger.info("selfRegistration method started");

		StudentDaoInterface studentDaoInterface = new StudentDaoOperation();
		return studentDaoInterface.addStudent(student);
	}
	
	public void viewGradeCard() {
		
	}
	
	/**
	 * 
	 * 
	 * @param sem, semester
	 * to list all courses available for semester sem
	 * 
	 * 
	 */
	public String viewCatalogue(int sem) {
		logger.info("viewCatalogue method started");

		return courseInterface.viewCourses(sem);
	}
	
	
	/**
	 * Print courses current user has registered for
	 * 
	 * @param sem, semester the current user wants to look the courses for
	 * 
	 * 
	 */
	public String viewRegisteredCourses(int sem) {
		logger.info("viewRegisteredCourses method started");

		//take course from student table
		return registeredCourse.printRegisteredCourses(UserService.currentUsedId, 1);
	}
	
	
	/**
	 * Method to register current user for semester sem
	 * 
	 * @param sem, semester to register the user in
	 * @return boolean true if registration successful, otherwise false
	 *
	 * 
	 * 
	 * 
	 */
	public boolean semesterRegistration(int sem) {
		logger.info("semesterRegistration method started");

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
	
	
	/**
	 * 
	 * Register the student (currently logged in) for course with courseId in semester sem
	 * @param courseId
	 * @param sem
	 * 
	 * @return boolean returns true if registering for course is successful, otherwise false
	 * 
	 */
	public boolean addCourse(String courseId,int sem) throws AddCourseLimitExceed{
		logger.info("addCourse method started");

		try {
			boolean val=registeredCourse.addCourse(courseId,UserService.currentUsedId,sem);
			if(val) {
				System.out.println("Course added successfully");
				return true;
			}
			else {
				System.out.println("Course is not added, Try again");
			}
			return false;
		} catch (AddCourseLimitExceed e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * Deregister the student (currently logged in) for course with courseId in semester sem
	 * @param courseId
	 * @param sem
	 * 
	 * @return boolean returns true if de-registering for course is successful, otherwise false
	 * 
	 */
	public boolean dropCourse(String StudentId, String courseId)
	{
		logger.info("dropCourse method started");

		try {
			boolean val=registeredCourse.dropCourse(StudentId, courseId);
			if(val) {
			System.out.println("Course dropped successfully");
			return true;
			}
			else {
			System.out.println("Course is not dropped, Try again");
			}
		} catch (CourseNotEndrolledByStudent e) {
			// TODO Auto-generated catch block
			System.out.println("Course is not endrolled by student: "+e.getCourseId());
		}
		return false;
	}

	
}
