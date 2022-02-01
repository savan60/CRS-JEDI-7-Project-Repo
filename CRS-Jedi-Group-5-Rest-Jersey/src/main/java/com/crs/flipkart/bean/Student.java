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
	private boolean isApproved;
	private String phoneNumber;
	private String address;
	
	
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
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public int getCurrent_semester() {
		return current_semester;
	}

	public void setCurrent_semester(int current_semester) {
		this.current_semester = current_semester;
	}
	private int current_semester;
	
	public Student(String studentId, String email, String name, String number, String address, UserType type, String password) {
		super(studentId, email, password, UserType.Student);
		this.studentId = studentId;
		this.name = name;
		this.phoneNumber=number;
		this.address=address;
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
