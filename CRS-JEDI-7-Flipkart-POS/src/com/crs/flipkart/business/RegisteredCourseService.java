/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Arrays;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;

/**
 * @author ADARSH
 *
 */
public class RegisteredCourseService implements RegisteredCourseInterface{
//	public static RegisteredCourse[] reg= {,new RegisteredCourse("101", "100","101", 0, 1),new RegisteredCourse("102", "200","302", 0, 1)};
	
	public static ArrayList<RegisteredCourse> reg =  new ArrayList<RegisteredCourse>(Arrays.asList(new RegisteredCourse("100", "100","100", 0, 1), new RegisteredCourse("102", "200","302", 0, 1)));
	
	public void submitGrade(String courseId,String studentId,float newGrade) {
		for(int i=0;i<reg.size();i++) {
			if(reg.get(i).getCourseId().equals(courseId) && reg.get(i).getStudentId().equals(studentId)) {
				reg.get(i).setGrade(newGrade);
				System.out.println("Grade updated");
				return;
			}
		}
		System.out.println("Student doesn't enrolled in your course");
		
	}
}
