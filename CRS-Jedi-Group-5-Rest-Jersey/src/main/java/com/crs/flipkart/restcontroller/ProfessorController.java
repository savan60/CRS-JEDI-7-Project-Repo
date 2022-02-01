/**
 * 
 */
package com.crs.flipkart.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;

<<<<<<< HEAD
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

=======
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
>>>>>>> 28ce3df6fd5e548f7c9d4aebf06e9e854a090a12
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.utils.Pair;

/**
<<<<<<< HEAD
 * @author ADARSH
 *
 */

@Path("/professorApi")
public class ProfessorController {
	ProfessorInterface professorInterface = new ProfessorService();

	@GET
	@Path("/viewEnrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, ArrayList<Pair>> viewEnrolledStudents(@QueryParam("professorId") String professorId) {
		HashMap<String, ArrayList<Pair>> result = professorInterface.viewEnrolledStudents(professorId);
		return result;
	}

	@GET
	@Path("/viewCourses/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> viewCourses(@PathParam("professorId") String professorId) {
		HashMap<String, String> res = professorInterface.viewCourse(professorId);
		return res;
	}

	@PUT
	@Path("/addGrade/{professorId}/{grade}/{studentId}/{courseId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGrade(@PathParam("professorId") String professorId, @PathParam("grade") float grade,
			@PathParam("studentId") String studentId, @PathParam("courseId") String courseId) {
		String result;
		if (professorInterface.addGrade(professorId, grade, studentId, courseId)) {
			result = "Grade is assigned";
			return Response.status(201).entity(result).build();
		} else {
			result = "Grade assigned failed";
			return Response.status(500).entity(result).build();
		}
	}

	@PUT
	@Path("/addCourse/{professorId}/{courseId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("professorId") String professorId, @PathParam("courseId") String courseId) {
		String result;
		if (professorInterface.addCourse(professorId, courseId)) {
			result = "Professor assigned to this course";
			return Response.status(201).entity(result).build();
		} else {
			result = "Assigned failed";
			return Response.status(500).entity(result).build();
		}
	}
=======
 * @author DELL NOTEBOOK
 *
 */

//viewenrolledstudents
		// assigngrades
		// addcourse
		// dropcourse
		// update password
		// logout
@Path("/professorApi")
public class ProfessorController {
>>>>>>> 28ce3df6fd5e548f7c9d4aebf06e9e854a090a12

}
