/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Map;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author SAVAN
 *
 */
public interface UserInterface {

	public String forgotPassword(String email,String phoneNumber) ;
	/*
	 * rests the password of the user
	 */
	public boolean createNewPassword(String password,String userId);
	/*
	 * updates the Userpassword in the database.
	 */
//	public void getUsers() ;
	public boolean checkPasswordforEmail(String password);
	public UserType authenticate(String email,String password);
	/*
	 * checks weather the entered password and the email id belogings to the same user
	 *and returns the type of user logging in
	 */
}
