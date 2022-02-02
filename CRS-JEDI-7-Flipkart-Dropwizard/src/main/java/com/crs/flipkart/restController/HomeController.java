package com.crs.flipkart.restController;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.exceptions.UserNotFoundException;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HomeController {
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	    public String getEmployees(User user) {
			UserInterface adminOperation = new UserService();
			try {
				adminOperation.authenticate("parth@mail.com", "abcd");
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return user.getEmail();
	    }

}