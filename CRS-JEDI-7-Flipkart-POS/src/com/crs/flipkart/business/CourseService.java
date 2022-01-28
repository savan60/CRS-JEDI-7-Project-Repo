package com.crs.flipkart.business;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.CourseDaoOperation;
import com.crs.flipkart.dao.UserDaoInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.exceptions.CourseNotAddedException;
import com.crs.flipkart.exceptions.CourseNotDeletedException;

/**
 * @author parth
 *
 */
public class CourseService implements CourseInterface {
	private static Logger logger=Logger.getLogger(CourseService.class);
	
	CourseDaoInterface courseInterface=new CourseDaoOperation();
//	logger.info("Instance creation of courseDaoOperation");
	
	private  ArrayList<Course> courses = CourseDaoOperation.getAllCourses();
	
	/**
	 * @return the courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public boolean addCourse(String id,String subj,float duration ,float credits) {
		for(int i=0;i<courses.size();i++){
			if((courses.get(i)).getCourseId().equals(id))return false;	
		}
		try {
			courseInterface.addCourToDB(id,subj,duration,credits);
		} catch (CourseNotAddedException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return true;
	}
	
	public boolean deleteCourse(String id) {
		ArrayList<Course> listOfCourses = CourseDaoOperation.getAllCourses();
		for(int i=0;i<listOfCourses.size();i++){
			if((listOfCourses.get(i)).getCourseId().equals(id)) {
				try {
					courseInterface.delCourse(id);
				} catch (CourseNotDeletedException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
				return true;
			}	
		}
		return false;
	}
	
//	public boolean addCourse(String id,String subj,float duration ,float credits) {
//		if(courses.get(id)!=null) {
////			courses.put(id, new Course(id, subj, duration , credits));
//			return true;
//		}
//		
//		return false;
//	}
	
	
//	
//	public boolean removeCourse(String id) {
//		if(courses.get(id)!=null) {
//			courses.remove(id);
//			return true;
//		}
//		
//		return false;
//	}
	
}