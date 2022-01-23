package com.crs.flipkart.business;

import java.sql.Date;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.utils.Utils.UserType;

public class ProfessorService {
//	private ArrayList<Student> listOfEnrolledStudents=new ArrayList<>();
//	String id, String name, long number, String add, UserType type, String pass, String professorId,
//	String department, Date doj, String position, String courseId
	RegisteredCourseService regCourseService=new RegisteredCourseService();
	private Professor[] listOfProfessor= {new Professor("100","100"),new Professor("101","101"),new Professor("102","102")};
	private RegisteredCourse[] reg=RegisteredCourseService.reg;

	public void viewEnrolledStudents(String professorId) {
		String courseId="";
		System.out.println("professorId is "+professorId);

		for(Professor p:listOfProfessor) {
			if(professorId==p.getProfessorId()) {
				courseId=p.getCourseId();
				break;
			}
		}
		System.out.println("courseid is "+courseId);
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
