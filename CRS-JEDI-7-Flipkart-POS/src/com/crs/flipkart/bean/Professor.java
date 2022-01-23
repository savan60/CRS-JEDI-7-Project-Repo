/**
 * 
 */
package com.crs.flipkart.bean;


import java.util.Date;
import java.util.ArrayList;

import com.crs.flipkart.utils.Utils.UserType;

public class Professor extends User{
	private String professorId;
	private String department;
	private Date doj;
	private String position;
	private String courseId;
	
	public Professor(String professorId, String courseId) {
		super(professorId, "name", 12234,"india", UserType.Professor,"abcd");
		this.professorId = professorId;
		this.department = "CSEC";
		this.doj = new Date();
		this.position = "Headmaster";
		this.courseId = courseId;
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
	 * @return the professorId
	 */
	public String getProfessorId() {
		return professorId;
	}
	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the doj
	 */
	public Date getDoj() {
		return doj;
	}
	/**
	 * @param doj the doj to set
	 */
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
}

