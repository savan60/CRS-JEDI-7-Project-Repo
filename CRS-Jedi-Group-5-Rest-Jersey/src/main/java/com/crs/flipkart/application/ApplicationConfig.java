/**
 * 
 */
package com.crs.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.restcontroller.AdminController;
import com.crs.flipkart.restcontroller.ProfessorController;
import com.crs.flipkart.restcontroller.StudentController;

//import org.glassfish.jersey.server.ResourceConfig;





public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {

		register(AdminController.class);

		register(ProfessorController.class);

		register(StudentController.class);

	}

}