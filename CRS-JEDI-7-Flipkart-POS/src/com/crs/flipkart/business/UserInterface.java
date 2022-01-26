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
	
	
	public boolean createNewPassword(String password,String userId);
	public void getUsers() ;
	public boolean checkPasswordforEmail(String password);
	public UserType authenticate(String email,String password);
}
