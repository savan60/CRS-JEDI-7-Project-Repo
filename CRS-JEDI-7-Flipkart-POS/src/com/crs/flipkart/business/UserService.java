/**
 * 
 */
package com.crs.flipkart.business;

import java.util.*;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.dao.UserDaoInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author parth
 *
 */
public class UserService implements UserInterface{
	
	public static String currentUsedId;
	UserDaoInterface userInterface=new UserDaoOperation();

	public String forgotPassword(String email,String phoneNumber) {
		return userInterface.getUserIdByEmailAndPhoneNumber(email,phoneNumber);
	}
	
	
	public boolean createNewPassword(String password,String userId) {
		return userInterface.updatePassword(password, userId);
	}
	
//	public void getUsers() {
//		
//	}
	public boolean checkPasswordforEmail(String password) {
		return userInterface.checkPasswordByUserId(currentUsedId,password);
	}
	
	public UserType authenticate(String email,String password) {
		return userInterface.authenticate(email, password);
	}

}


