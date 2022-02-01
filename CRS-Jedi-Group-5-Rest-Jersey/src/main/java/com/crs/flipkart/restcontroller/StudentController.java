package com.crs.flipkart.restcontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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

import org.json.JSONException;
import org.json.JSONObject;

import com.crs.flipkart.bean.DummyPro;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

@Path("/studentApi")
public class StudentController {
	
//GEt method which is using for fetch
	
	
//	post method implementation
	@POST
	@Path("/semesterRegistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response SemesterRegistration (String t) throws JSONException{
		
	        HashMap<String, String> map = new HashMap<String, String>();
	        JSONObject jObject = new JSONObject(t);
	        Iterator<?> keys = jObject.keys();

	        while( keys.hasNext() ){
	            String key = (String)keys.next();
	            String value = jObject.getString(key); 
	            map.put(key, value);
	        }

	        System.out.println("json : "+jObject);
	        System.out.println("map : "+map);
	        StudentInterface studentOperation = StudentService.getInstance();
	        
	        UserService.currentUsedId=map.get("userID");
	        
	    int sem=Integer.parseInt(map.get("sem"));
	    studentOperation.setSemester(map.get("userID"),sem);
	    String result="";
		boolean val = studentOperation.semesterRegistration(sem);
		if (val) {
			boolean res1 = studentOperation.addCourse(map.get("courseID1"), sem);
			boolean res2 =studentOperation.addCourse(map.get("courseID2"), sem);
			boolean res3 =studentOperation.addCourse(map.get("courseID3"), sem);
			boolean res4 =studentOperation.addCourse(map.get("courseID4"), sem);
			boolean res5 =studentOperation.addCourse(map.get("courseID5"), sem);
			boolean res6 =studentOperation.addCourse(map.get("courseID6"), sem);
			if(res1 && res2 && res3 && res4 && res5 && res6) {
				result="Registration Successful";
				return Response.status(201).entity(result).build();
			}
		}
		result="Registration UnSuccessful";
		return Response.status(409).entity(result).build();
		
	} 

	@DELETE
	@Path("/delete/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") int customerId)
	throws URIReferenceException{

		// service query to perfomr the delete operation
		
		// implementation
		return Response.status(200).entity("Track id " +customerId +
				"successfully deleted").build();
		
	
	}
	
	

}