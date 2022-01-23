package com.crs.flipkart.business;

import java.sql.Date;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.utils.Utils.UserType;

public class ProfessorService {
	RegisteredCourseService regCourseService=new RegisteredCourseService();
	private Professor[] listOfProfessor= {new Professor("100","100"),new Professor("101","101"),new Professor("102","102")};
	private RegisteredCourse[] reg=RegisteredCourseService.reg;

	public void viewEnrolledStudents(String professorId) {
		String courseId="";
		for(Professor p:listOfProfessor) {
			if(professorId==p.getProfessorId()) {
				courseId=p.getCourseId();
				break;
			}
		}
		for(int i=0;i<reg.length;i++) {
			if(reg[i].getCourseId()==courseId) {
				System.out.println("Student Id--->"+reg[i].getStudentId()+" Grade--> "+reg[i].getGrade());
			}
		}

	}
	public void addGrade(String professorId,float grade,String studentId) {

		String courseId="";
		for(Professor p:listOfProfessor) {
			if(professorId==p.getProfessorId()) {
				courseId=p.getCourseId();
				break;
			}
		}
		regCourseService.submitGrade(courseId,studentId,grade);
	}
}
