package com.crs.flipkart.restcontroller;

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


@Path("/adminApi")
public class AdminController {
	
//GEt method which is using for fetch
	
	
//	post method implementation
	@POST
	@Path("/post")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddProfessor(DummyPro name) {
        System.out.println("hit post service");
        
        System.out.println("value of title from UI " +name.getPhoneNumber());
        System.out.println("value of singer form UI" +name.getDepartment());
        
        String result = "Track saved : ";
		
		
		return Response.status(201).entity(result).build();
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