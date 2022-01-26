/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author S.V.S.SUDHEEP RAO
 *
 */
public class Student {
	private String studentId;//pk => userid
	private String name;
	
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
