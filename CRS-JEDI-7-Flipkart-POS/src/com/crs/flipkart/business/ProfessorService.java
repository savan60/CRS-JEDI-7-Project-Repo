package com.crs.flipkart.business;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;
import com.crs.flipkart.utils.Utils.UserType;

public class ProfessorService implements ProfessorInterface {
	RegisteredCourseInterface regCourseService = new RegisteredCourseService();
	CourseInterface courseService = new CourseService();
	CourseDaoInterface courseInterface=new CourseDaoOperation();

	public void viewEnrolledStudents(String professorId) {
		ArrayList<String> courseIds = CourseDaoOperation.fetchCourseIdFromProfessorId(professorId);
		for (String courseId : courseIds) {
			System.out.println("Students enrolled in course: " + courseId);
			RegisteredCourseDaoOperation.printEnrolledStudentInThatCourse(courseId);
		}

	}

	public void addGrade(String professorId, float grade, String studentId, String courseId) {
		boolean flag = false;
		ArrayList<String> courseIds = CourseDaoOperation.fetchCourseIdFromProfessorId(professorId);
		for (String cId : courseIds) {
			if (cId.equals(courseId)) {
				flag = true;
				break;
			}
		}

		if (flag) {
			regCourseService.submitGrade(courseId, studentId, grade);
		} else {
			System.out.println("Professor " + professorId + " does not teach " + courseId + " course.");
		}
	}

	public void addCourse(String professorId, String courseId) {
		courseInterface.updateProfessorId(professorId, courseId);
	}

	public void viewCourse(String professorId) {

		ArrayList<String> courseIds = CourseDaoOperation.fetchCourseIdFromProfessorId(professorId);

		System.out.println("List of Course:");
		for (String cId : courseIds) {
			System.out.println(cId);
		}

	}

}
