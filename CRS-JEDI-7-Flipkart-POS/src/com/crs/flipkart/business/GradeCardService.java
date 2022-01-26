/**
 * 
 */
package com.crs.flipkart.business;
import com.crs.flipkart.bean.RegisteredCourse;
import java.util.ArrayList;
import com.crs.flipkart.dao.GradeCardDaoOperation;
/**
 * @author nikhil
 *
 */
public class GradeCardService implements GradeCardInterface{

	//don't call calgrade anywhere else
	public float calGrade(ArrayList<RegisteredCourse> courses) {
		
		float totalSum = 0;
		int totalSubjects =  courses.size();
		if(totalSubjects==0) {
			return 0;
		}
		
		for(RegisteredCourse course : courses) {
			totalSum+=course.getGrade();
		}
		return totalSum/totalSubjects;
	}
	
	public void viewGradeCard(String studentID, int semester) {
		ArrayList<RegisteredCourse> courses = GradeCardDaoOperation.fetchRegisteredSemesterCoursesForStudents(studentID, semester)
;
		float finalGrade = calGrade(courses);
		System.out.println("GRADE CARD FOR SEMESTER : "+Integer.toString(semester));
		System.out.println("STUDENT ID : "+studentID );
		System.out.println("CourseID\tScore");
		for(RegisteredCourse course : courses) {
			System.out.println(course.getCourseId()+" "+Float.toString(course.getGrade()));
			System.out.println("-----------------------------");
		}
		System.out.println("Semester Grade Point Average :"+" "+Float.toString(finalGrade));
		
		
	}
}
