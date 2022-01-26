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
	static HashMap<String,User> user=new HashMap<>(){{
		put("100",new User("100","Prof1@mail.com",12345,"Bangalore",UserType.Professor,"abcd"));
		put("101",new User("101","Prof2@mail.com",12345,"Hyderabad",UserType.Professor,"abcd"));
		put("102",new User("102","admin@mail.com",12345,"Hyderabad",UserType.Admin,"abcd"));
		}};
										
	
	public static String currentUsedId;
	Scanner in = new Scanner(System.in); 
	
	public boolean addUser(String id, String email, Long num, String add, String pass,UserType type) {
		
		if(user.get(id)==null) {
			user.put(id,new User(id,email,num,add,type,pass));
			return true;
		}
		
		return false;
	}
	
	public String forgotPassword(String email,long phoneNumber) {
		for(Map.Entry u:user.entrySet()) {
			if(email.equals(((User) u.getValue()).getEmail()) && phoneNumber == ((User) u.getValue()).getPhoneNumber()) {
				return (String) u.getKey();
			}
		}
		return "0";
	}
	
	
	public void createNewPassword(String password,String userId) {
		(user.get(userId)).setPassword(password);
	}
	
	public void getUsers() {
		
	}
	public String getEmailByUserId(String userId) {
		return (user.get(userId)).getEmail();
	}
	
	public UserType authenticate(String email,String password) {

		for(Map.Entry u:user.entrySet()) {
			if(email.equals(((User) u.getValue()).getEmail()) && password.equals(((User) u.getValue()).getPassword())) {
				currentUsedId=(String) u.getKey();
				return  ((User) u.getValue()).getUserType();
			}
		}
		return UserType.None;
	}

}


