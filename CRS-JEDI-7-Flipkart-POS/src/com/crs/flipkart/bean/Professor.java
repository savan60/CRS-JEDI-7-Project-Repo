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
	private ArrayList<String> myCourseIds = new ArrayList<String>();
	
	public Professor(String professorId) {
		super(professorId, "name", 12234,"india", UserType.Professor,"abcd");
		this.professorId = professorId;
		
		this.department = "CSEC";
		this.doj = new Date();
		this.position = "Headmaster";
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
	/**
	 * @return the myCourses
	 */
	public ArrayList<String> getMyCourses() {
		return myCourseIds;
	}
	/**
	 * @param myCourses the myCourses to set
	 */
	public void setMyCourses(ArrayList<String> myCourses) {
		this.myCourseIds = myCourses;
	}
	
	public void addToMyCourses(String courseId) {
		this.myCourseIds.add(courseId);
	}
}

