package com.crs.flipkart.business;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//import java.util.Stream;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;
import com.crs.flipkart.exceptions.GradeCardByCourseIdFoundEmpty;
import com.crs.flipkart.utils.Pair;
import com.crs.flipkart.utils.Utils.UserType;

public class ProfessorService implements ProfessorInterface {
	RegisteredCourseInterface regCourseService = new RegisteredCourseService();
	CourseInterface courseService = new CourseService();
	CourseDaoInterface courseInterface = new CourseDaoOperation();
	RegisteredCourseDaoInterface registeredCourseDaoInterface = new RegisteredCourseDaoOperation();
	private static volatile ProfessorService instance = null;
	private static Logger logger=Logger.getLogger(ProfessorService.class);


	/**
	 * 
	 * 
	 * create a instance of ProfessorService
	 *
	 * @return instance of the ProfessorService
	 * 
	 */
	public static ProfessorService getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminService.class){
				instance = new ProfessorService();
			}
		}
		return instance;
	}


	/**
	 * 
	 * Returns details of students enrolled in courses taught by Professor, grouped by courseId
	 * 
	 * @param professorId
	 * @return HashMap<String, ArrayList<Pair>>
	 * 
	 */
	public HashMap<String, ArrayList<Pair>> viewEnrolledStudents(String professorId) {
		logger.info("viewEnrolledStudents method started");

		System.out.println(professorId);
		ArrayList<String> courseIds = courseInterface.fetchCourseIdFromProfessorId(professorId);
		HashMap<String, ArrayList<Pair>> MapOfEnrolledStudents = new HashMap<String, ArrayList<Pair>>();
		courseIds.forEach((courseId) -> {
			System.out.println("Students enrolled in course: " + courseId);
			try {
				ArrayList<Pair> list = registeredCourseDaoInterface.printEnrolledStudentInThatCourse(courseId);
				MapOfEnrolledStudents.put(courseId, list);

			} catch (GradeCardByCourseIdFoundEmpty e) {
				// TODO Auto-generated catch block
				System.out.println("Grade card of courseId " + e.getCourseId());
			}
		});
		return MapOfEnrolledStudents;

	}


	/**
	 * Method to assign grade to student with studentId for course with courseId
	 * 
	 * 
	 * @param ProfessorId
	 * @param grade
	 * @param studentId
	 * @param courseId
	 * 
	 * 
	 */
	public boolean addGrade(String professorId, float grade, String studentId, String courseId) {
		logger.info("addGrade method started");

		boolean flag = false;
		ArrayList<String> courseIds = courseInterface.fetchCourseIdFromProfessorId(professorId);
		for (String cId : courseIds) {
			if (cId.equals(courseId)) {
				flag = true;
				break;
			}
		}

		if (flag) {
			if (regCourseService.submitGrade(courseId, studentId, grade))
				return true;
		} else {
			System.out.println("Professor " + professorId + " does not teach " + courseId + " course.");
		}
		return false;
	}

	/**
	 * 
	 * Method to assign a course with courseId to professor with professorId
	 * 
	 * @param professorId
	 * @param courseId
	 * 
	 * 
	 * 
	 * 
	 */
	public boolean addCourse(String professorId, String courseId) {
		logger.info("addCourse method started");

		if (courseInterface.updateProfessorId(professorId, courseId))
			return true;
		return false;
	}


	/**
	 * Method to get list of all the courses that professor with professorId teaches
	 * 
	 * @param professorId
	 * 
	 * 
	 */
	public HashMap<String, String> viewCourse(String professorId) {
		logger.info("viewCourse method started");

		HashMap<String, String> res=new HashMap<String, String>();
		ArrayList<String> courseIds = courseInterface.fetchCourseIdFromProfessorId(professorId);
		if (courseIds.isEmpty()) {
			System.out.println("No courses assigned to you");
			return res;
		}
		System.out.println("List of Courses:");
		System.out.println("CourseId\tCourseName\tDuration\tCredits");
		for (String courseId : courseIds) {
			Course course = courseInterface.getCourseFromId(courseId);
			res.put(courseId, course.getName());
		}
		return res;
	}

}
