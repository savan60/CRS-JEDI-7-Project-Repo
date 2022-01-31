/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.Date;
import java.util.ArrayList;

import com.crs.flipkart.utils.Utils.UserType;

public class Professor extends User {
	private String professorId;// pk =>userid
	private String department;
	private Date doj;
	private String position;
	private String phoneNumber;
	private String address;
	private ArrayList<String> myCourseIds = new ArrayList<String>();// database m nahi ayega


	public Professor(String professorId,String email,String number,String address,String password, String department, Date doj, String position) {
		super(professorId, email, password, UserType.Professor);
		this.professorId = professorId;
		this.department = department;
		this.doj = doj;
		this.phoneNumber=number;
		this.address=address;
		this.position = position;
	}
	
	public Professor() {
		super();
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Professor(String professorId, String department, Date doj, String position) {
		super(professorId,"","",UserType.Professor);
		this.professorId = professorId;
		this.department = department;
		this.doj = doj;
		this.position = position;
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
