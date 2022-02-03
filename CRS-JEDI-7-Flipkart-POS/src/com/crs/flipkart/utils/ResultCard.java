/**
 * 
 */
package com.crs.flipkart.utils;

/**
 * @author ADARSH
 *
 */
public class ResultCard{
	private String courseId;
	private String courseName;
	private float grade;
	public ResultCard(String courseId, String courseName, float grade) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.grade = grade;
	}
	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
}