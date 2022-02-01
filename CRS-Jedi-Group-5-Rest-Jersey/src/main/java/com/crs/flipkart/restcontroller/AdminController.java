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

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.DummyPro;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
<<<<<<< HEAD
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.utils.Pair;
=======
import com.crs.flipkart.business.CourseInterface;
import com.crs.flipkart.business.CourseService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
>>>>>>> 28ce3df6fd5e548f7c9d4aebf06e9e854a090a12
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
<<<<<<< HEAD

=======
>>>>>>> 28ce3df6fd5e548f7c9d4aebf06e9e854a090a12



// addCourse, removeCourse, generateGradeCard, updatePassword, approveStudent, semesterFees, logout
@Path("/adminApi")
public class AdminController {
	
//GEt method which is using for fetch
	
	
//	post method implementation
	@POST
	@Path("/addprofessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddProfessor(Professor professor) {
        System.out.println("In Addprofessor");

		AdminInterface adminOperation = new AdminService();
		String result;

        if(adminOperation.addProfessor( professor.getEmail(),  professor.getPhoneNumber(),  professor.getAddress(),  professor.getPassword(),  professor.getDepartment(),  professor.getPosition())) {
        	result="Professor is added";
        	return Response.status(201).entity(result).build();
        }
        else {
        	result="Professor not added";
        	return Response.status(409).entity(result).build();
<<<<<<< HEAD
        }
	} 
	
	

	
=======
        }		
	}
	
	@POST
	@Path("/addcourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddCourse(Course course) {
        System.out.println("In Addcourse");

		CourseInterface courseOperation = CourseService.getInstance();

        if(courseOperation.addCourse( course.getCourseId(), course.getName(), course.getDuration(), course.getCredits())) {

        	return Response.status(201).entity("Course added").build();
        }
        else {
        	
        	return Response.status(201).entity("Course already exists, please try again!").build();
        }		
	} 
	
	@POST
	@Path("/deletecourse")
	@Consumes("text/plain")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(String courseName) {
		
	
		CourseInterface courseOperation = CourseService.getInstance();
		
		if(courseOperation.deleteCourse(courseName)) {
			
			return Response.status(201).entity("Course removed.").build();
		}
		else {
			        	
			 return Response.status(201).entity("Course id does not exist, please try again!").build();
		}		
	}
>>>>>>> 28ce3df6fd5e548f7c9d4aebf06e9e854a090a12
	
	@PUT
	@Path("/approvestudents")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ApproveStudents() {
        System.out.println("In ApproveAllStudents");
        AdminInterface adminOperation = AdminService.getInstance();
		
        if(adminOperation.approveAllStudents("0"))
        	return Response.status(201).entity("All students are approved.").build();
        else
        	return Response.status(201).entity("Students could not be approved!").build();
     	
	}
	
	@PUT
	@Path("/approvestudents/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ApproveStudents(@PathParam("studentId") String studentId) {
        System.out.println("In ApproveAllStudentsById");
        AdminInterface adminOperation = AdminService.getInstance();
        if(adminOperation.approveAllStudents(studentId))
        	return Response.status(201).entity("Student approved.").build();
        else
        	return Response.status(201).entity("Student could not be approved!").build();
     	
	}
	
	@PUT
	@Path("/generategradecard/{semester}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GenerateGradeCard(@PathParam("semester") String semester) {
          System.out.println("In GenerateReportCard");

          System.out.println(semester);
        
          AdminInterface adminOperation = AdminService.getInstance();
          adminOperation.genReportCard(Integer.parseInt(semester));
          return Response.status(201).entity("Report card generated.").build();
	}
	
	@PUT
	@Path("/semesterfees/{amount}/{message}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GenerateGradeCard(@PathParam("amount") String amount, @PathParam("message") String message) {
        
          PaymentInterface paymentOperation = PaymentService.getInstance();
          paymentOperation.askForPayment(Integer.parseInt(amount),message);
          return Response.status(201).entity("Payment added to student.").build();
	}
	
	
	
//	@DELETE
//	@Path("/deletecourse/{name}")
////	@Consumes("text/plain")
////	@Produces(MediaType.TEXT_PLAIN)
//	public Response RemoveCourse(@PathParam("name") String name) {
//        System.out.println("In Removecourse");
//        System.out.println(name);
//		CourseInterface courseOperation = CourseService.getInstance();
//
//        if(courseOperation.deleteCourse(name)) {
//
//        	return Response.status(201).entity("Course removed.").build();
//        }
//        else {
//        	
//        	return Response.status(201).entity("Course id does not exist, please try again!").build(); // Course is not being removed correctly from the database in the backend
//        }		
//	} 
	
	
	

}