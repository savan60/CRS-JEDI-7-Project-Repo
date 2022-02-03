package com.crs.flipkart.business;
import java.util.ArrayList;
import org.apache.log4j.Logger;


import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.exceptions.CourseNotAddedException;
import com.crs.flipkart.exceptions.CourseNotDeletedException;

/**
 * @author parth
 *
 */
public class CourseService implements CourseInterface {
	private static Logger logger=Logger.getLogger(CourseService.class);
	CourseDaoInterface courseInterface=new CourseDaoOperation();
	
	private static volatile CourseService instance = null;

	/**
	 * 
	 * 
	 * create a instance of CourseService
	 *
	 * @return instance of the CourseService
	 * 
	 */
	public static CourseService getInstance()
	{
		if(instance == null)
		{
			synchronized(CourseService.class){
				instance = new CourseService();
			}
		}
		return instance;
	}

	/**
	 * 
	 * Method to add a new course to the catalogue
	 * 
	 * @param id, unique courseId
	 * @param subj, name of the course
	 * @param duration, duration of the course in days
	 * @param credits, credits that will be assigned to student after successfully completing the course
	 * 
	 * 
	 * @return boolean, true if course added successfully, otherwise false
	 * 
	 * 
	 */
	public boolean addCourse(String id,String subj,float duration ,float credits) {
		logger.info("You are adding this course: "+subj);
		ArrayList<Course> courses = CourseDaoOperation.getAllCourses();
		for(int i=0;i<courses.size();i++){
			if((courses.get(i)).getName().equals(subj))
				return false;
		}
		try {
			courseInterface.addCourToDB(id,subj,duration,credits);
		} catch (CourseNotAddedException e) {
			logger.error("Error message: "+e.getMessage());
		}
		return true;
	}
	
	/**
	 * Method to delete course from catalogue
	 * 
	 * @param id, course id for the course to be deleted
	 * @return boolean, true if course is deleted successfully otherwise false
	 * 
	 * 
	 */
	public boolean deleteCourse(String name) {
		logger.info("you are deleting this course: "+name);
		ArrayList<Course> listOfCourses = CourseDaoOperation.getAllCourses();
		for(int i=0;i<listOfCourses.size();i++){
			if((listOfCourses.get(i)).getName().equals(name)) {
				try {
					courseInterface.delCourse(name);
				} catch (CourseNotDeletedException e) {
					// TODO Auto-generated catch block
					logger.error("Error message: "+e.getMessage());
				}
				return true;
			}	
		}
		return false;
	}
	

	
}