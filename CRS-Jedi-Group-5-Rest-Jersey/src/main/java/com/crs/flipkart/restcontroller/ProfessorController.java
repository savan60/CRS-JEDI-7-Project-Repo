/**
 * 
 */
package com.crs.flipkart.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.utils.Pair;

/**
 * @author ADARSH
 *
 */

@Path("/professorApi")
public class ProfessorController {
	ProfessorInterface professorInterface=new ProfessorService();
	@GET
	@Path("/viewEnrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, ArrayList<Pair>> getCustomerDetails() {
		HashMap<String, ArrayList<Pair>> result=professorInterface.viewEnrolledStudents("100");
		return result;
	}
	@GET
	@Path("/viewCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> viewCourses(){
		ArrayList<String> list=professorInterface.viewCourse("100");
		return list;
	}
	
	
	@PUT
	@Path("/addGrade")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGrade() {
		String result;
		if(professorInterface.addGrade("100", 8, "0379fd","103" )) {
			result="Grade is assigned";
        	return Response.status(201).entity(result).build();
		}
		else {
			result="Grade assigned failed";
			return Response.status(500).entity(result).build();
		}
	}
	
	@PUT
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse() {
		String result;
		if(professorInterface.addCourse("100", "104")) {
			result="Professor assigned to this course";
        	return Response.status(201).entity(result).build();
		}
		else {
			result="Assigned failed";
			return Response.status(500).entity(result).build();
		}
	}
	
}
