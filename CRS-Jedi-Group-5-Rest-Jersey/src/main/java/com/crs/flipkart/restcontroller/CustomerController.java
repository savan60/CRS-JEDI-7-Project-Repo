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


@Path("/CustomerAPI")
public class CustomerController {
	
//GEt method which is using for fetch
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerDetails() {

		//  clinet --- service ---- dao ----> SQL
       
		Customer customer=new Customer();
		customer.setId(101);
		customer.setName("Flipcard");
		customer.setAddress("mumbai");
		
	   return customer;
	}
	
	
//	post method implementation
	@POST
	@Path("/post")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(String json) {
//        System.out.println("hit post service");
//        
//        System.out.println("value of title from UI " +customer.getId());
//        System.out.println("value of singer form UI" +customer.getName());
//		
//        
//        String result = "Track saved : " + customer;
		
		System.out.println(json);
		return Response.status(201).entity("track").build();
		
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
	
	
	@PUT
	@Path("/update")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer updateCustomer(Customer customer){
		
		// rest of impl write here in code 
		
		customer.setName(customer.getName() +"updated");
		return customer;
		
	}

}