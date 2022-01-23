package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.bean.Student;

public class ProfessorService {
//	private ArrayList<Student> listOfEnrolledStudents=new ArrayList<>();
	private Professor[] listOfProfessor= {new Professor(),new Professor(),new Professor()};
	private RegisteredCourse[] reg=RegisteredCourseService.reg;
	
	public void viewEnrolledStudents(Course course) {
		for(int i=0;i<reg.length;i++) {
			if(reg[i].getCourseId()==course.getCourseId()) {
				System.out.println("Student Id--->"+reg[i].getStudentId()+" Registered Id--> "+reg[i].getRegisteredCourseId());
			}
		}

	}
	public void addGrade(Student student,int newGrade,Course course) {
		for(int i=0;i<reg.length;i++) {
			if(reg[i].getCourseId()==course.getCourseId() && reg[i].getStudentId()==student.getStudentId()) {
				reg[i].setGrade(newGrade);
			}
		}
	}
}
