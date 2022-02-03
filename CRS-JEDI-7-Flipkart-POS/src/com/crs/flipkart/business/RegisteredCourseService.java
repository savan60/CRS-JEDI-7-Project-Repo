/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Arrays;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.dao.RegisteredCourseDaoInterface;
import com.crs.flipkart.dao.RegisteredCourseDaoOperation;

/**
 * @author ADARSH
 *
 */
public class RegisteredCourseService implements RegisteredCourseInterface {

	RegisteredCourseDaoInterface registeredCourseDaoInterface =new RegisteredCourseDaoOperation();

	public boolean submitGrade(String courseId, String studentId, float newGrade) {

		if (registeredCourseDaoInterface.updateGrade(courseId, studentId, newGrade))
			return true;
		return false;

	}
}
