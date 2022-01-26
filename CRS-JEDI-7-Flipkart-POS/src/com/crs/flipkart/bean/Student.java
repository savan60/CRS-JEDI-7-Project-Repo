/**
 * 
 */
package com.crs.flipkart.bean;

import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author S.V.S.SUDHEEP RAO
 *
 */
public class Student extends User{
	
	private String studentId;//pk => userid
	private String name;
	
	public Student(String studentId, String name, long number, String address, UserType type, String password) {
		super(studentId, name, number, address, type, password);
		this.studentId = studentId;
		this.name = name;
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
	 * @return the emailId
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
