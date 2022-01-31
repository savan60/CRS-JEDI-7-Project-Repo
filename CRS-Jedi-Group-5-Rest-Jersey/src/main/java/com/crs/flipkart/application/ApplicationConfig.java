/**
 * 
 */
package com.crs.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.restcontroller.AdminController;

//import org.glassfish.jersey.server.ResourceConfig;


/**
 * @author Mehak Goel
 *
 */



public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {

		register(AdminController.class);
	}

}