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
	
	private static volatile GradeCardService instance = null;

	public static GradeCardService getInstance()
	{
		if(instance == null)
		{
			synchronized(GradeCardService.class){
				instance = new GradeCardService();
			}
		}
		return instance;
	}
	
	public String viewGradeCard(String studentID, int semester) {
    /*
     * Method to print score of a student with studentId in Semester semester
     * @param studentId, semester
     *
     */
		
		ArrayList<RegisteredCourse> courses = gradeCardInterface.fetchRegisteredSemesterCoursesForStudents(studentID, semester)
;
		try {
			String res = "";
			GradeCard gradeCard = gradeCardInterface.fetchGradeCard(studentID, semester);
			float finalGrade = gradeCard.getGrade();
			res += "GRADE CARD FOR SEMESTER : "+Integer.toString(semester) + "\n";
			res += "STUDENT ID : "+studentID+" NAME: "+studentInterface.getStudentNameFromId(studentID) + "\n";
			res += "CourseID\t Course Name\tScore\n";
			
			for(RegisteredCourse course : courses) {
				res += course.getCourseId()+"\t"+courseInterface.getCourseFromId(course.getCourseId()).getName()+"\t"+Float.toString(course.getGrade()) + "\n";
				res += "-----------------------------\n";
			}
			res += "Semester Grade Point Average :"+" "+Float.toString(finalGrade) + "\n";
			return res;	
		}
		catch(GradeCardNotCreatedException ex) {
			logger.error("GRADECARDNOTCREATED "+ex.getMessage());
		}
		return null;
	}
}