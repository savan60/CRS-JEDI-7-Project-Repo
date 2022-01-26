/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.Vector;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author SAVAN
 *
 */
public interface UserDaoInterface {
	public UserType authenticate(String email,String password);
	
	public String getUserIdByEmailAndPhoneNumber(String email,String phoneNumber);
	
	public boolean updatePassword(String password,String userId);
	
	public boolean checkPasswordByUserId(String userId,String password);
}
