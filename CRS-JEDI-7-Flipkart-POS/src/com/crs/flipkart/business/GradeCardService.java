/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.exceptions.GradeCardNotCreatedException;
import org.apache.log4j.Logger;

import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.GradeCardDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.bean.RegisteredCourse;
import java.util.ArrayList;
import com.crs.flipkart.dao.GradeCardDaoOperation;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.bean.GradeCard;
/**
 * @author nikhil
 *
 */
public class GradeCardService implements GradeCardInterface{

	private static Logger logger = Logger.getLogger(GradeCardService.class);
	
	CourseDaoInterface courseInterface = new CourseDaoOperation();
	StudentDaoInterface studentInterface = new StudentDaoOperation();
	GradeCardDaoInterface gradeCardInterface = new GradeCardDaoOperation();
	
	public void viewGradeCard(String studentID, int semester) {
    /*
     * Method to print score of a student with studentId in Semester semester
     * @param studentId, semester
     *
     */
		
		ArrayList<RegisteredCourse> courses = gradeCardInterface.fetchRegisteredSemesterCoursesForStudents(studentID, semester)
;
		try {
			
			GradeCard gradeCard = gradeCardInterface.fetchGradeCard(studentID, semester);
			float finalGrade = gradeCard.getGrade();
			System.out.println("GRADE CARD FOR SEMESTER : "+Integer.toString(semester));
			System.out.println("STUDENT ID : "+studentID+" NAME: "+studentInterface.getStudentNameFromId(studentID));
			System.out.println("CourseID\t Course Name\tScore");
			
			for(RegisteredCourse course : courses) {
				System.out.println(course.getCourseId()+"\t"+courseInterface.getCourseFromId(course.getCourseId()).getName()+"\t"+Float.toString(course.getGrade()));
				System.out.println("-----------------------------");
			}
			System.out.println("Semester Grade Point Average :"+" "+Float.toString(finalGrade));
		}catch(GradeCardNotCreatedException ex) {
			logger.error("GRADECARDNOTCREATED "+ex.getMessage());
		}
	}
}
