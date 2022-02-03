/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;

/**
 * @author ADARSH
 *
 */
public class RegisteredCourseService implements RegisteredCourseInterface {

	RegisteredCourseDaoInterface registeredCourseDaoInterface = new RegisteredCourseDaoOperation();
	private static Logger logger=Logger.getLogger(ProfessorService.class);

	/**
	 * 
	 * updates the grade of student with studentId for the course with courseId
	 * 
	 * @param courseId
	 * @param studentId
	 * @param newGrade 
	 * 
	 */
	public boolean submitGrade(String courseId, String studentId, float newGrade) {
		logger.info("submitGrade method started");

		if (registeredCourseDaoInterface.updateGrade(courseId, studentId, newGrade))
			return true;
		return false;

	}
}
