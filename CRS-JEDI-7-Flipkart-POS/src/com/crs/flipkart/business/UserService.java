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
	
	public void forgotPassword(UserType role,String email) {
		for(User u:user) {
			if(u.getUserType()==role && email.equals(u.getEmail())) {
				System.out.println("Enter your registered phone number to verify!");
				long phoneNo = in.nextLong();
				if(phoneNo == u.getPhoneNumber()) {
					boolean ischanged = createNewPassword(u);
					if(ischanged)
						System.out.println("Password has been changed. Login again!");
				}
				else System.out.println("Invalid Credentials!");
				return;
			}
		}
		System.out.println("Email dosen't exist!");
	}
	
	public void updatePassword(UserType role,String email) {
		for(User u:user) {
			if(u.getUserType()==role && email.equals(u.getEmail())) {
				boolean ischanged = createNewPassword(u);
				if(ischanged)
					System.out.println("Password has been updated. Login again!");
				else System.out.println("Error. Try again!");
				break;
			}
		}
	}
	
	private boolean createNewPassword(User u) {
		while(true) {
			System.out.println("Enter your choice:\n"+"1. Change Password\n2. Exit");
			int choice = in.nextInt();
			
			if(choice == 2) return false;
			else if(choice != 1) 
				System.out.println("Invalid Choice");
			else {
				String pass, newPass; 
				System.out.println("Type New Password!");
				pass = in.next();
				System.out.println("Re-Enter New Password!");
				newPass = in.next();
				if(pass.equals(newPass)) {
					u.setPassword(newPass);
					return true;
				}
				else System.out.println("Passowrd Mismatch. Try Again!");
			}
		}
	}
	
	public void getUsers() {
		
	}
	
	public boolean authenticate(UserType role,String email,String password) {
		for(User u:user) {
			if(u.getUserType()==role && email.equals(u.getEmail()) && password.equals(u.getPassword())) {
				currentUsedId=u.getUserId();
				return true;
			}
		}
		
		return false;
	}
	

}


