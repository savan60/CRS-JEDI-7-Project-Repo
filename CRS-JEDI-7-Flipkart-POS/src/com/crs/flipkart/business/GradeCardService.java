/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.exceptions.GradeCardNotCreatedException;
import com.crs.flipkart.utils.ResultCard;

import org.apache.log4j.Logger;

import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.GradeCardDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.constant.COLORCONSTANT;

import java.util.ArrayList;
import com.crs.flipkart.dao.GradeCardDaoOperation;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.bean.Course;
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
	
	public ArrayList<ResultCard> viewGradeCard(String studentID, int semester) {
    /*
     * Method to print score of a student with studentId in Semester semester
     * @param studentId, semester
     *
     */
		
		ArrayList<RegisteredCourse> courses = gradeCardInterface.fetchRegisteredSemesterCoursesForStudents(studentID, semester);
		ArrayList<ResultCard>res=new ArrayList<ResultCard>();
		try {
			
			GradeCard gradeCard = gradeCardInterface.fetchGradeCard(studentID, semester);
			float finalGrade = gradeCard.getGrade();
			
			for(RegisteredCourse course : courses) {
				res.add(new ResultCard(course.getCourseId(),courseInterface.getCourseFromId(course.getCourseId()).getName(),course.getGrade()));
			}
			System.out.println(COLORCONSTANT.TEXT_BLACK);
			System.out.println("----------------------------------------------------GRADE CARD-----------------------------------------------------------\n");
			System.out.println("CourseId    Grade    Course Name");
			res.forEach((r)->{
				System.out.println(r.getCourseId()+"      "+r.getGrade()+"      "+r.getCourseName());
			});
			System.out.println("Semester Grade Point Average :"+" "+Float.toString(finalGrade));
		}catch(GradeCardNotCreatedException ex) {
			logger.error("GRADECARDNOTCREATED "+ex.getMessage());
		}
		return res;
	}
}


