/**
 * 
 */
package com.crs.flipkart.utils;

/**
 * @author ADARSH
 *
 */
public class Pair {
	private String StudentName;
	private String StudentId;
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return StudentName;
	}
	public Pair(String studentName, String studentId) {
		super();
		StudentName = studentName;
		StudentId = studentId;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return StudentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	
	
}
