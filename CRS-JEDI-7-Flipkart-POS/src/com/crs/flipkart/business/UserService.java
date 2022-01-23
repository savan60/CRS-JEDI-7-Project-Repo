/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author parth
 *
 */
public class UserService {
//	String id,String name,String number,String add,UserType type
	static User[] user={new User("1","Prof1@mail.com",12345,"Bangalore",UserType.Professor,"abcd"),new User("2","Prof2@mail.com",123456,"Hyderabad",UserType.Professor,"efgh")};
	
	
	
	public void forgotPassword() {
		
	}
	
	public void updatePassword() {
			
	}
	
	public void getUsers() {
		
	}
	
	public boolean authenticate(UserType role,String email,String password) {
		
		for(User u:user) {
			System.out.println(u.getUserType());
			if(u.getUserType()==role && email.equals(u.getEmail()) && password.equals(u.getPassword())) {

				return true;
			}
		}
		
		return false;
	}
	

}


