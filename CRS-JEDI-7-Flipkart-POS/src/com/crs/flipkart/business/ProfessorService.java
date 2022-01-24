package com.crs.flipkart.business;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.utils.Utils.UserType;

public class ProfessorService implements ProfessorInterface {
	RegisteredCourseService regCourseService=new RegisteredCourseService();
	CourseService courseService = new CourseService();

//	private Professor[] listOfProfessor= {new Professor("100","100"),new Professor("101","101"),new Professor("102","102")};
	private ArrayList<Professor> listOfProfessor = new ArrayList<Professor>(Arrays.asList(new Professor("100"), new Professor("101"), new Professor("103")));
	private ArrayList<RegisteredCourse> reg = RegisteredCourseService.reg;
	private HashMap<String, Course> listOfCourses = courseService.getCourses();
	
	public void viewEnrolledStudents(String professorId) {
	
		ArrayList<String> courseIds = new ArrayList<String>();
		
		for(Professor p:listOfProfessor) {
			if(professorId==p.getProfessorId()) {
				courseIds = p.getMyCourses();
				break;
			}
		}
		
		for(String courseId : courseIds) {
			System.out.println("Students enrolled in course: " + courseId);
			for(int i=0;i<reg.size();i++) {
				if(reg.get(i).getCourseId().equals(courseId)) {
					System.out.println("Student Id--->"+reg.get(i).getStudentId()+" Grade--> "+reg.get(i).getGrade());
				}
			}
		}

	}
	
	public void addGrade(String professorId,float grade, String studentId, String courseId) {
		boolean flag = false;
		ArrayList<String> courseIds = new ArrayList<String>();
		
		for(Professor p:listOfProfessor) {
			if(professorId==p.getProfessorId()) {
				courseIds =p.getMyCourses();
				break;
			}
		}
		
		for(String cId : courseIds) {
			if(cId.equals(courseId)) {
				flag = true;
				break;
			}
		}
		
		if(flag) {
			regCourseService.submitGrade(courseId,studentId,grade);
		}
		else {
			System.out.println("Professor "+ professorId +" does not teach " + courseId +" course.");
		}
	}
	
	public void addCourse(String professorId, String courseId) {
		
		
		for(Professor p:listOfProfessor) {
			if(professorId==p.getProfessorId()) {
				p.addToMyCourses(courseId);
				listOfCourses.get(courseId).setProfessorId(professorId);
				break;
			}
		}		
	}
	
	
	public void viewCourse(String professorId) {
		
		ArrayList<String> courseIds = new ArrayList<String>();
		
		for(Professor p:listOfProfessor) {
			if(professorId==p.getProfessorId()) {
				courseIds = p.getMyCourses();
				break;
			}
		}
		
		System.out.println("List of Course:");
		for(String cId : courseIds) {
			System.out.println(cId);
		}
		
		
	}
	
}
