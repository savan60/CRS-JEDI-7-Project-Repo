/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author DELL NOTEBOOK
 *
 */
public class Course {
	
	private String courseId;//pk
	private String name;
	private float duration;
	private float credits;
	private String professorId;//fk
	
	
	
	public Course(String courseId, String professorId,String name, float duration, float credits) {
		super();
		this.courseId = courseId;
		this.professorId=professorId;
		this.name = name;
		this.duration = duration;
		this.credits = credits;
	}
	
	public Course() {
		super();
	}
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public float getCredits() {
		return credits;
	}
	public void setCredits(float credits) {
		this.credits = credits;
	}
	public String getProfessorId() {
		return professorId;
	}
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	
}
