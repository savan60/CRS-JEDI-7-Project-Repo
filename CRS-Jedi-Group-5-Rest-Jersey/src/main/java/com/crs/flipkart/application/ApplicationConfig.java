/**
 * 
 */
package com.crs.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.restcontroller.AdminController;
import com.crs.flipkart.restcontroller.ProfessorController;
import com.crs.flipkart.restcontroller.StudentController;
import com.crs.flipkart.restcontroller.UserController;

//import org.glassfish.jersey.server.ResourceConfig;




	/**
	 * 
	 * Register all rest controller here
	 * 
	 *
	 */
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {

		register(AdminController.class);

		register(ProfessorController.class);

		register(StudentController.class);
		register(UserController.class);

	}

}