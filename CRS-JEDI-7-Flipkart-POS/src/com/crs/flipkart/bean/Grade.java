package com.crs.flipkart.bean;

public class Grade {
	private String courseId;
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
	 * @return the grades
	 */
	public float getGrades() {
		return grades;
	}
	/**
	 * @param grades the grades to set
	 */
	public void setGrades(float grades) {
		this.grades = grades;
	}
	private String studentId;
	private float grades;
}
