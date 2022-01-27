package com.crs.flipkart.business;
import java.util.ArrayList;
import java.util.HashMap;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.CourseDaoOperation;

/**
 * @author parth
 *
 */
public class CourseService implements CourseInterface {

	private ArrayList<Course> courses = CourseDaoOperation.getAllCourses();
	
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
		CourseDaoOperation.addCourToDB(id,subj,duration,credits);
		return true;
	}
	public boolean deleteCourse(String id) {
		for(int i=0;i<courses.size();i++){
			if((courses.get(i)).getCourseId().equals(id)) {
				CourseDaoOperation.delCourse(id);
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