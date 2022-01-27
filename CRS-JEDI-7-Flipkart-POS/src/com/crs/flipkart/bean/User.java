/**
 * 
 */
package com.crs.flipkart.bean;

import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author parth
 *
 */
public class User {
	
	private String userId;//pk
	private String email;
	private String phoneNumber;
	private String address;
	private String password;
	private UserType userType;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String id,String email,String number,String add,String pass,UserType type) {
		this.userId=id;
		this.email=email;
		this.phoneNumber=number;
		this.address=add;
		this.password=pass;
		this.userType=type;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setEmail(String userName) {
		this.email = userName;
	}
	/**
	 * @return the phoneNumber
	 */
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
	
	
	

}
