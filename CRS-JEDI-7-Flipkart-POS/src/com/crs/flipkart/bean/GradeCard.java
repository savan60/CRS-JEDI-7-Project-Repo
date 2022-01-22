/**

 * 
 */
package com.crs.flipkart.bean
import com.crs.flipkart.Registered_Course;

/**
 * @author nikhil
 *
 */
public class GradeCard {
	private String studentID;
	private int semester;
	private float grade;
	private Registered_Course RegisteredCourse[] = new Registered_Course[] {};
	
	
	/**
	 * @return the registeredCourse
	 */
	public Registered_Course[] getRegisteredCourse() {
		return RegisteredCourse;
	}



	/**
	 * @param registeredCourse the registeredCourse to set
	 */
	public void setRegisteredCourse(Registered_Course registeredCourse) {
		RegisteredCourse = registeredCourse;
	}



	/**
	 * @return the grade
	 */
	public float getGrade() {
		return grade;
	}



	/**
	 * @param grade the grade to set
	 */
	public void setGrade(float grade) {
		this.grade = grade;
	}


	
	
	/**
	 * @return the studentID
	 */
	public String getStudentID() {
		return studentID;
	}



	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}



	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}



	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}

}
