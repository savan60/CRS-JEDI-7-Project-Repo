package com.crs.flipkart.restcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.DummyPro;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.utils.Pair;
import com.crs.flipkart.utils.Utils;
import com.crs.flipkart.business.CourseInterface;
import com.crs.flipkart.business.CourseService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

// API for administrator functionalities
@Path("/adminApi")
public class AdminController {
	private static Logger logger=Logger.getLogger(AdminController.class);

 	/**
	 * Adds professor
	 * 
	 * @param professor
	 */
	@POST
	@Path("/addprofessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddProfessor(Professor professor) {
		logger.info("AddProfessor method started");
		AdminInterface adminOperation = new AdminService();
		String result;
		int res = adminOperation.addProfessor( professor.getEmail(),  professor.getPhoneNumber(),  professor.getAddress(),  professor.getPassword(),  professor.getDepartment(),  professor.getPosition());
        if(res == 1) {
        	result="Professor is added";
        	return Response.status(201).entity(result).build();
        }
        else if(res == 2) {
        	result="Professor with this email already exists!";
        	return Response.status(409).entity(result).build();
        }
        else {
        	result="Professor not added";
        	return Response.status(409).entity(result).build();
        }
	} 
	
	/**
	 * Adds course
	 * 
	 * @param course
	 */
	@POST
	@Path("/addcourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddCourse(Course course) {
		logger.info("AddCourse method started");

		CourseInterface courseOperation = CourseService.getInstance();
        if(courseOperation.addCourse(Utils.generateUniqueId().substring(0,3) + Utils.generateUniqueId().substring(10,13), course.getName(), course.getDuration(), course.getCredits()))
        	return Response.status(201).entity("Course added").build();
        else 
        	return Response.status(201).entity("Course already exists, please try again!").build();		
	} 
	
	
	/**
	 * Delete course
	 * 
	 * @param name
	 */
	@DELETE
	@Path("/deletecourse/{name}")
	public Response RemoveCourse(@PathParam("name") String name) {
		logger.info("RemoveCourse method started");

		CourseInterface courseOperation = CourseService.getInstance();
        if(courseOperation.deleteCourse(name)) 
        	return Response.status(201).entity("Course removed.").build();
        else
        	return Response.status(201).entity("Course does not exist, please try again!").build();		
	} 
	
	/**
	 * Approve students
	 * 
	 */
	@PUT
	@Path("/approvestudents")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ApproveStudents() {
		logger.info("ApproveStudents method started");

        AdminInterface adminOperation = AdminService.getInstance();		
        if(adminOperation.approveAllStudents("0"))
        	return Response.status(201).entity("All students are approved.").build();
        else
        	return Response.status(201).entity("Students could not be approved!").build();
     	
	}
	
	/**
	 * Approve student by id
	 * 
	 */
	@PUT
	@Path("/approvestudents/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ApproveStudents(@PathParam("studentId") String studentId) {
		logger.info("ApproveStudents with student id method started");

        AdminInterface adminOperation = AdminService.getInstance();
        if(adminOperation.approveAllStudents(studentId))
        	return Response.status(201).entity("Student approved.").build();
        else
        	return Response.status(201).entity("Student could not be approved!").build();
     	
	}
	
	/**
	 * Generate grade card
	 * 
	 */
	@PUT
	@Path("/generategradecard/{semester}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GenerateGradeCard(@PathParam("semester") String semester) {
		logger.info("GenerateGradeCard method started");

          AdminInterface adminOperation = AdminService.getInstance();
          adminOperation.genReportCard(Integer.parseInt(semester));
          return Response.status(201).entity("Report card generated.").build();
	}
	
	/**
	 * Semester fees
	 * 
	 */
	@PUT
	@Path("/semesterfees/{amount}/{message}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response SemesterFees(@PathParam("amount") String amount, @PathParam("message") String message) {
		logger.info("SemesterFees method started");

          PaymentInterface paymentOperation = PaymentService.getInstance();
          paymentOperation.askForPayment(Integer.parseInt(amount),message);
          return Response.status(201).entity("Payment added to student.").build();
	}
	

}