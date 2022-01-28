/**
 * 
 */
package com.crs.flipkart.business;

import java.util.*;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.dao.UserDaoInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.exceptions.PasswordNotMatchException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.exceptions.phoneNumberNotMatchException;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author parth
 *
 */
public class UserService implements UserInterface{
	
	private static Logger logger = Logger.getLogger(UserService.class);
	
	public static String currentUsedId;
	UserDaoInterface userInterface=new UserDaoOperation();

	public UserType authenticate(String email,String password) {
		logger.info("Inside UserService: Authentication Started!");
		UserType u = UserType.None;
		try {
			u = userInterface.authenticate(email, password);
			logger.debug("Authentication Successful!");
			return u;
		} catch (PasswordNotMatchException e) {
			logger.error("Wrong Password for Email: "+e.getuserCredential());
		} catch (UserNotFoundException e) {
			logger.error("User Not Found: "+e.getuserCredential());
		}
		return u;
	}
	
	public String forgotPassword(String email,String phoneNumber) {
		logger.info("Inside UserService: In forgotPassword Functionality!");
		String id = "0";
		try {
			id = userInterface.getUserIdByEmailAndPhoneNumber(email,phoneNumber);
			logger.debug("User Found!");
		} catch (phoneNumberNotMatchException e) {
			logger.error("Wrong Phone Number for Email: "+e.getuserCredential());
		} catch (UserNotFoundException e) {
			logger.error("User Not Found with Email: "+e.getuserCredential());
		}
		return id;
	}
	
	
	public boolean createNewPassword(String password,String userId) {
		logger.info("Inside UserService: In createNewPassword Functionality!");
		boolean created = false;
		created = userInterface.updatePassword(password, userId);
		logger.info("Password Updated!");
		return created;
	}
	
	public boolean checkPasswordforEmail(String password) {
		logger.info("Inside UserService: In checkPasswordforEmail Functionality!");
		boolean check = false;
		try {
			check = userInterface.checkPasswordByUserId(currentUsedId,password);
			logger.debug("User Checked!");
		} catch (PasswordNotMatchException e) {
			logger.error("Wrong Password for UserId: "+e.getuserCredential());
		} catch (UserNotFoundException e) {
			logger.error("User Not Found with UserId: "+e.getuserCredential());
		}
		return check;
	}
	
}


