/**
 * 
 */
package com.crs.flipkart.bean;

import java.sql.Timestamp;

/**
 * @author S.V.S.SUDHEEP RAO
 *
 */
public class RegisteredCourse {
	private String registeredCourseId;//pk
	private String courseId;//fk
	private String studentId;//fk
	private float grade;
	private int semester;
	private Timestamp timeStamp;
	private boolean isAllocated;
	
	
	
	public RegisteredCourse(String registeredCourseId, String courseId, String studentId, float grade, int semester,
			Timestamp timeStamp) {
		super();
		this.registeredCourseId = registeredCourseId;
		this.courseId = courseId;
		this.studentId = studentId;
		this.grade = grade;
		this.semester = semester;
		this.timeStamp = timeStamp;
		this.isAllocated = false;
	}
	
	/**
	 * @return the registeredCourseId
	 */
	public String getRegisteredCourseId() {
		return registeredCourseId;
	}
	/**
	 * @param registeredCourseId the registeredCourseId to set
	 */
	public void setRegisteredCourseId(String registeredCourseId) {
		this.registeredCourseId = registeredCourseId;
	}
	
	
	public RegisteredCourse(String registeredCourseId, String courseId, String studentId, float grade, int semester) {
		super();
		this.registeredCourseId = registeredCourseId;
		this.courseId = courseId;
		this.studentId = studentId;
		this.grade = grade;
		this.semester = semester;
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
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
