/**
 * 
 */
package com.crs.flipkart.business;
import com.crs.flipkart.bean.RegisteredCourse;
import java.util.ArrayList;
import com.crs.flipkart.dao.GradeCardDaoOperation;
import com.crs.flipkart.bean.GradeCard;
/**
 * @author nikhil
 *
 */
public class GradeCardService implements GradeCardInterface{

	public void viewGradeCard(String studentID, int semester) {
		ArrayList<RegisteredCourse> courses = GradeCardDaoOperation.fetchRegisteredSemesterCoursesForStudents(studentID, semester)
;
		GradeCard gradeCard = GradeCardDaoOperation.fetchGradeCard(studentID, semester);
		if(gradeCard.getGradeCardId().equalsIgnoreCase("NOTFOUND")) {
			System.out.println("RESULT NOT PREPARED YET");
			return;
		}
		float finalGrade = gradeCard.getGrade();
		System.out.println("GRADE CARD FOR SEMESTER : "+Integer.toString(semester));
		System.out.println("STUDENT ID : "+studentID);
		System.out.println("CourseID\tScore");
		
		for(RegisteredCourse course : courses) {
			System.out.println(course.getCourseId()+" "+Float.toString(course.getGrade()));
			System.out.println("-----------------------------");
		}
		System.out.println("Semester Grade Point Average :"+" "+Float.toString(finalGrade));
	}
}
