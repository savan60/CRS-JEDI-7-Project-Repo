/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.Vector;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.exceptions.PasswordNotMatchException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.exceptions.phoneNumberNotMatchException;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author SAVAN
 *
 */
public interface UserDaoInterface {
	public User authenticate(String email,String password) throws UserNotFoundException, PasswordNotMatchException;
	
	public String getUserIdByEmailAndPhoneNumber(String email,String phoneNumber) throws UserNotFoundException, phoneNumberNotMatchException;
	
	public boolean updatePassword(String password,String userId);
	
	public boolean checkPasswordByUserId(String userId,String password) throws UserNotFoundException, PasswordNotMatchException;
	
	public void addUser(User user);
	
	public boolean IsStudentApproved(String userId);
}
