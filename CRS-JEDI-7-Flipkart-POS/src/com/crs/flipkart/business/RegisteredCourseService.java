/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.RegisteredCourse;

/**
 * @author ADARSH
 *
 */
public class RegisteredCourseService {
	public static RegisteredCourse[] reg= {new RegisteredCourse("100", "100","100", 0, 1),new RegisteredCourse("101", "100","101", 0, 1),new RegisteredCourse("102", "200","302", 0, 1)};
	
	public void submitGrade(String courseId,String studentId,float newGrade) {
		for(int i=0;i<reg.length;i++) {
			if(reg[i].getCourseId().equals(courseId) && reg[i].getStudentId().equals(studentId)) {
				reg[i].setGrade(newGrade);
				System.out.println("Grade updated");
				return;
			}
		}
		System.out.println("Student doesn't enrolled in your course");
		
	}
}
