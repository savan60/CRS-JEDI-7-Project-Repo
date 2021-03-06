/**
 * 
 */
package com.crs.flipkart.business;

import java.util.*;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.ReturnStatus;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.dao.UserDaoInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.exceptions.PasswordNotMatchException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.exceptions.phoneNumberNotMatchException;
import com.crs.flipkart.utils.Pair;
import com.crs.flipkart.utils.Utils.LoginStatus;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author parth
 *
 */

public class UserService implements UserInterface{
	
	private static Logger logger = Logger.getLogger(UserService.class);
	
	public static String currentUsedId;
	UserDaoInterface userInterface=new UserDaoOperation();
	private static volatile UserService instance = null;

	
	/**
	 * 
	 * 
	 * create a instance of UserService
	 *
	 * @return instance of the UserService
	 * 
	 */
	public static UserService getInstance()
	{
		if(instance == null)
		{
			synchronized(UserService.class){
				instance = new UserService();
			}
		}
		return instance;
	}

	/**
	 * 
	 * Facilitates logging in, after successful login returns type of user
	 * 
	 * 
	 * @param email
	 * @param password
	 * @return UserType 
	 * 
	 */
	
	public ReturnStatus authenticate(String email,String password) {
		logger.info("Inside UserService: Authentication Started!");
		User user;
		ReturnStatus ret=new ReturnStatus();
		try {
			user = userInterface.authenticate(email, password);
			if(user.getUserType()==null) {
				ret.setMessage("User Login Failed,Try again");
				ret.setStatus(300);
				return ret;
			}
			UserType u=user.getUserType();
			if(u==UserType.Student) {
				if(userInterface.IsStudentApproved(user.getUserId())) {
					ret.setMessage("User Login Success");
					ret.setStatus(200);
					ret.setResponse(user.getUserId());
					return ret;
				}
				else {
					System.out.println("Student is not approved!");
					ret.setMessage("Student is not approved,wait till admin approves you!");
					ret.setStatus(300);
					return ret;
				}
			}
			ret.setMessage("User Login Success");
			ret.setStatus(200);
			ret.setResponse(user.getUserId());
			logger.debug("Authentication Successful!");
			return ret;
		} catch (PasswordNotMatchException e) {
			logger.error("Wrong Password for Email: "+e.getuserCredential());
		} catch (UserNotFoundException e) {
			logger.error("User Not Found: "+e.getuserCredential());
		}
		ret.setMessage("Invalid credentials,Try again");
		ret.setStatus(300);
		return ret;
	}
	
	
	
	/**
	 * 
	 * Method to reset password for the user currently logged in
	 * 
	 * @param email
	 * @param phoneNumber
	 * 
	 * @return String 
	 * 
	 */
	public String forgotPassword(String email,String phoneNumber) throws phoneNumberNotMatchException,UserNotFoundException {
		logger.info("Inside UserService: In forgotPassword Functionality!");
		String id = "0";
		try {
			id = userInterface.getUserIdByEmailAndPhoneNumber(email,phoneNumber);
			logger.debug("User Found!");
		} catch (phoneNumberNotMatchException e) {
			throw new phoneNumberNotMatchException(e.getuserCredential());
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(e.getuserCredential());
		}
		return id;
	}
	
	
	/**
	 * 
	 * set new password for user with userId
	 * 
	 * @param password
	 * @param userId
	 * @return boolean  true if updating password is successful, otherwise false
	 * 
	 * 
	 */
	public boolean createNewPassword(String password,String userId) {
		logger.info("Inside UserService: In createNewPassword Functionality!");
		boolean created = false;
		created = userInterface.updatePassword(password, userId);
		logger.info("Password Updated!");
		return created;
	}
	
	
	/**
	 * 
	 * checks if password for the user is correct
	 * 
	 * @param password
	 * 
	 * @return boolean true if password is correct, otherwise false
	 * 
	 */
	public boolean checkPasswordforEmail(String password,String userId) throws UserNotFoundException, PasswordNotMatchException {
		logger.info("Inside UserService: In checkPasswordforEmail Functionality!");
		boolean check = false;
		try {
			check = userInterface.checkPasswordByUserId(userId,password);
			logger.debug("User Checked!");
		} catch (PasswordNotMatchException e) {
			throw e;
//			logger.error("Wrong Password for UserId: "+e.getuserCredential());
		} catch (UserNotFoundException e) {
			throw e;
//			logger.error("User Not Found with UserId: "+e.getuserCredential());
		}
		return check;
	}

	
	
}


