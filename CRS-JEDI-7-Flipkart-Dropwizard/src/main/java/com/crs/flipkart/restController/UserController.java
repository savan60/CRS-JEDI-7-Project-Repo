/**
 * 
 */
package com.crs.flipkart.restController;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.ReturnStatus;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.exceptions.PasswordNotMatchException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.exceptions.phoneNumberNotMatchException;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.utils.Utils.LoginStatus;
import com.crs.flipkart.utils.Utils.UserType;

/**
 * @author SAVAN
 *
 */
@Path("/userAPI")
public class UserController {
	private static Logger logger=Logger.getLogger(StudentController.class);

	
	/**
	 * login
	 * 
	 * @param user
	 */

	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		logger.info("login method started");

		UserInterface adminOperation = new UserService();
		String result;
		try {
			ReturnStatus ret = adminOperation.authenticate(user.getEmail(), user.getPassword());
			if(ret.getStatus()==200) {
				result=ret.getMessage()+" user id is "+ret.getResponse();
				return Response.status(201).entity(result).build();
			}
			else {
				result=ret.getMessage();
				return Response.status(300).entity(result).build();
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			return Response.status(300).entity(e.getMessage()).build();
		}
	} 
	
	
	/**
	 * Register student
	 * 
	 * @param student
	 */
	@POST
	@Path("/studentRegister")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response studentRegister(Student student) {
		logger.info("studentRegister method started");
        String studentId = Utils.generateUniqueId().substring(0, 3)
				+ Utils.generateUniqueId().substring(10, 13);
        student.setUserId(studentId);
        student.setUserType(UserType.Student);
        student.setStudentId(studentId);
		StudentInterface studentInterface = new StudentService();
		String result;
		boolean ret = studentInterface.selfRegistration(student);
		if(ret) {
			result="Student added succesfully!";
			return Response.status(201).entity(result).build();
		}
		else {
			result="Student is not added, try again";
			return Response.status(300).entity(result).build();
		}
	} 
	
	/**
	 * Forget password
	 * 
	 * @param student
	 */
	@POST
	@Path("/forgetPassword")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgetPassword(Student student) {
		logger.info("forgetPassword method started");
       
		UserInterface user = new UserService();
		String result;
		String ret;
		try {
			ret = user.forgotPassword(student.getEmail(),student.getPhoneNumber());
			if(ret.equals("0")) {
				result="Invalid credentials";
				return Response.status(300).entity(result).build();
			}
			else {
				result="User verified. user id: "+ret;
				return Response.status(200).entity(result).build();
			}
		} catch (phoneNumberNotMatchException e) {
			// TODO Auto-generated catch block
			return Response.status(300).entity("Wrong Phone Number for Email: "+e.getuserCredential()).build();
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			return Response.status(300).entity("User Not Found with Email: "+e.getuserCredential()).build();
		}		
	} 
	
	
	/**
	 * change password
	 * 
	 * @param user
	 */
	@POST
	@Path("/changePassword")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(User user) {
		logger.info("changePassword method started");
		UserInterface userinterface = new UserService();
		String result;
		boolean ret;
		ret = userinterface.createNewPassword(user.getPassword(), user.getUserId());
		if(ret) {
			result="Password changed.";
			return Response.status(200).entity(result).build();
		}
		else {
			result="Password is not changed, try again";
			return Response.status(300).entity(result).build();
		}	
	} 
	
	
	/**
	 * update password
	 * 
	 * @param user
	 */
	@POST
	@Path("/updatePassword")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePassword(User user) {
		logger.info("updatePassword method started");
		UserInterface userinterface = new UserService();
		String result;
		boolean ret;
		try {
			ret = userinterface.checkPasswordforEmail(user.getPassword(), user.getUserId());
			if(ret) {
				result="Password verified. Now create new password";
				return Response.status(200).entity(result).build();
			}
			else {
				result="Password is not verified. Try again!";
				return Response.status(300).entity(result).build();
			}	
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			return Response.status(300).entity("Wrong Password for UserId: "+e.getuserCredential()).build();
		} catch (PasswordNotMatchException e) {
			// TODO Auto-generated catch block
			return Response.status(300).entity("User Not Found with UserId: "+e.getuserCredential()).build();
		}
		
	} 
}
