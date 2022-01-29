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
		
	/**
	 * @return the courses
	 */
//	public ArrayList<Course> getCourses() {
//		return courses;
//	}

	/**
	 * @param courses the courses to set
	 */
	
//	public void setCourses(ArrayList<Course> courses) {
//		this.courses = courses;
//	}

	public boolean addCourse(String id,String subj,float duration ,float credits) {
		logger.info("you are adding this course"+subj);
		ArrayList<Course> courses = CourseDaoOperation.getAllCourses();
		for(int i=0;i<courses.size();i++){
			if((courses.get(i)).getName().equals(subj))return false;	
		}
		try {
			courseInterface.addCourToDB(id,subj,duration,credits);
		} catch (CourseNotAddedException e) {
			logger.error("Error message: "+e.getMessage());
		}
		return true;
	}
	
	public boolean deleteCourse(String id) {
		logger.info("you are deleting this course"+id);
		ArrayList<Course> listOfCourses = CourseDaoOperation.getAllCourses();
		for(int i=0;i<listOfCourses.size();i++){
			if((listOfCourses.get(i)).getName().equals(id)) {
				try {
					courseInterface.delCourse(id);
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