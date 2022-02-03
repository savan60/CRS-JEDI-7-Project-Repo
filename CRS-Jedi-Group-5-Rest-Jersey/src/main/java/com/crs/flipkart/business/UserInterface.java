/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Map;

import com.crs.flipkart.bean.ReturnStatus;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.exceptions.PasswordNotMatchException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.exceptions.phoneNumberNotMatchException;
import com.crs.flipkart.utils.Utils.LoginStatus;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author SAVAN
 *
 */
public interface UserInterface {

	public String forgotPassword(String email,String phoneNumber) throws phoneNumberNotMatchException,UserNotFoundException ;
	/*
	 * rests the password of the user
	 */
	public boolean createNewPassword(String password,String userId);
	/*
	 * updates the Userpassword in the database.
	 */
//	public void getUsers() ;
	public ReturnStatus authenticate(String email,String password) throws UserNotFoundException;
	public boolean checkPasswordforEmail(String password,String userId) throws UserNotFoundException, PasswordNotMatchException;

	/*
	 * checks weather the entered password and the email id belogings to the same user
	 *and returns the type of user logging in
	 */
}
