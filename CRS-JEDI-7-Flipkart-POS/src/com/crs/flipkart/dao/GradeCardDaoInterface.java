/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.exceptions.GradeCardNotCreatedException;


/**
 * @author SAVAN
 *
 */
public interface GradeCardDaoInterface {
	public void gradeCardGen(int sem);
	public ArrayList<RegisteredCourse> fetchRegisteredSemesterCoursesForStudents(String studentId, int semester);
	public GradeCard fetchGradeCard(String studentId, int semester) throws GradeCardNotCreatedException;
}
