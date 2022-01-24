/**
 * 
 */
package com.crs.flipkart.business;

import java.util.*;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author parth
 *
 */
public class UserService {
//	String id,String name,String number,String add,UserType type
	static User[] user={new User("100","Prof1@mail.com",12345,"Bangalore",UserType.Professor,"abcd"),new User("101","Prof2@mail.com",123456,"Hyderabad",UserType.Professor,"efgh")};
	
	public static String currentUsedId;
	Scanner in = new Scanner(System.in); 
	
	public String forgotPassword(String email,long phoneNumber) {
		for(User u:user) {
			if(email.equals(u.getEmail()) && phoneNumber == u.getPhoneNumber()) {
				return u.getUserId();
//				if() {
//					boolean ischanged = createNewPassword(u);
//					if(ischanged)
////						System.out.println("Password has been changed. Login again!");
//				}
//				else System.out.println("Invalid Credentials!");
//				return;
			}
		}
		return "0";
	}
	
	public void updatePassword(UserType role,String email) {
		for(User u:user) {
//			if(u.getUserType()==role && email.equals(u.getEmail())) {
////				boolean ischanged = createNewPassword(u);
//				if(ischanged)
//					System.out.println("Password has been updated. Login again!");
//				else System.out.println("Error. Try again!");
//				break;
//			}
		}
	}
	
	public void createNewPassword(String password,String userId) {
		for(User u:user) {
			if(userId.equals(u.getUserId())) {
				u.setPassword(password);
			}
		}
	}
	
	public void getUsers() {
		
	}
	
	public UserType authenticate(String email,String password) {
		for(User u:user) {
			if(email.equals(u.getEmail()) && password.equals(u.getPassword())) {
				currentUsedId=u.getUserId();
				return u.getUserType();
			}
		}
		
		return UserType.None;
	}
	

}


