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

import com.crs.flipkart.bean.DummyPro;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.utils.Pair;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;



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
        }
	} 
	
	

	
	
	

}