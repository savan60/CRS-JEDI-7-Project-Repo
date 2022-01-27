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
public String forgotPassword(String email,long phoneNumber) ;
	
	
	public void createNewPassword(String password,String userId);
	public void getUsers() ;
	public String getEmailByUserId(String userId) ;
	public UserType authenticate(String email,String password);
//	public boolean checkPasswordforEmail(String password);
}
