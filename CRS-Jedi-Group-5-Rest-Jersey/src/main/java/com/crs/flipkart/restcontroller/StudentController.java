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

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Card;
import com.crs.flipkart.bean.DummyPro;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.AdminService;
import com.crs.flipkart.business.GradeCardInterface;
import com.crs.flipkart.business.GradeCardService;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.PaymentService;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.exceptions.AddCourseLimitExceed;
import com.crs.flipkart.utils.Utils.CardType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

@Path("/studentApi")
public class StudentController {
	
//GEt method which is using for fetch
private static Logger logger=Logger.getLogger(StudentController.class);

	/**
	 * Semester Registration
	 * 
	 */

	@POST
	@Path("/semesterRegistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response SemesterRegistration (String t) {
		logger.info("SemesterRegistration method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}

			logger.debug("map : "+map);
	        StudentInterface studentOperation = StudentService.getInstance();
	        UserService.currentUsedId=map.get("userID");
	        
	    int sem=Integer.parseInt(map.get("sem"));
	    studentOperation.setSemester(map.get("userID"),sem);
	    String result="";	    
		boolean val = studentOperation.semesterRegistration(sem);
		if(val) {
			result="Registration Successful";
			return Response.status(201).entity(result).build();
		}
		else {
			result="Student is already registered!";
			return Response.status(409).entity(result).build();
		}
		
	} 
	
	/**
	 * Course Registration
	 * 
	 */
	@POST
	@Path("/courseRegistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response CourseRegistration (String t) {
		logger.info("CourseRegistration method started");

			Map<String, String> map = new HashMap<String, String>();
			ObjectMapper mapper = new ObjectMapper();
	
			try {
				// convert JSON string to Map
				map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
				});
			} catch (Exception e) {
				return Response.status(409).entity("Registration failed, Try again!").build();
			}
			logger.debug("map : "+map);
	        StudentInterface studentOperation = StudentService.getInstance();
	        UserService.currentUsedId=map.get("userID");
	        String result="";
	        
	    int sem=Integer.parseInt(map.get("sem"));

			try {
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
			} catch (AddCourseLimitExceed e) {
				result = "You can't add courses more than: "+e.getCourse();
				return Response.status(409).entity(result).build();
			}
			
		result="Registration UnSuccessful";
		return Response.status(409).entity(result).build();
	} 
	
	
	/**
	 * Add Course
	 * 
	 */
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse (String t) {
		logger.info("addCourse method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}
			logger.debug("map : "+map);
	        StudentInterface studentOperation = StudentService.getInstance();
	        
	        UserService.currentUsedId=map.get("userID");	        
		    int sem=Integer.parseInt(map.get("sem"));	
		    String courseId=map.get("courseId");
		    String result="";
			boolean val;
			try {
				val = studentOperation.addCourse(courseId,sem);
				if (val) {
					result="Course added successfully";
					return Response.status(201).entity(result).build();
				}
			} catch (AddCourseLimitExceed e) {
				result = "You can't add courses more than: "+e.getCourse();
				return Response.status(409).entity(result).build();
			}
			
			result="Course is not added, Try again";
			return Response.status(409).entity(result).build();
	}
	
	/**
	 * Drop Course
	 * 
	 */
	@POST
	@Path("/dropCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response dropCourse (String t) {
		logger.info("dropCourse method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}
	        logger.debug("map : "+map);
	        StudentInterface studentOperation = StudentService.getInstance();
	        
	        String userID=UserService.currentUsedId=map.get("userID");	        
//		    int sem=Integer.parseInt(map.get("sem"));	
		    String courseId=map.get("courseId");
		    String result="";
			boolean val = studentOperation.dropCourse(userID,courseId);
			
			if (val) {
				result="Course dropped successfully";
				return Response.status(201).entity(result).build();
			}
			
			result="Course is not dropped, Try again";
			return Response.status(409).entity(result).build();
		
	}
	
	
	/**
	 * View catalogue
	 * 
	 */
	@POST
	@Path("/viewCatalogue")
	@Consumes("application/json")
	@Produces(MediaType.TEXT_HTML)
	public Response viewCatalogue (String t) {
		logger.info("viewCatalogue method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}
			logger.debug("map : "+map);
	        StudentInterface studentOperation = StudentService.getInstance();
	        
		    int sem=Integer.parseInt(map.get("sem"));	
		    String result="";
			String val = studentOperation.viewCatalogue(sem);
			
			if (val==null) {
				result="No courses to display";
				return Response.status(409).entity(result).build();
				
			}
			
			
			return Response.status(201).entity(val).build();
		
	}
	
	/**
	 * View registered course
	 * 
	 */
	@POST
	@Path("/viewRegisteredCourses")
	@Consumes("application/json")
	@Produces(MediaType.TEXT_HTML)
	public Response ViewRegisteredCourses (String t) {
		logger.info("ViewRegisteredCourses method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}
	    StudentInterface studentOperation = StudentService.getInstance();
	    
	    UserService.currentUsedId=map.get("userID");
	    StudentService.current_semester=Integer.parseInt(map.get("current_semester"));
	    
	    String res=studentOperation.viewRegisteredCourses(StudentService.current_semester);


	    if(res==null) {
	    	res="Cannot view registered courses";
		     return Response.status(409).entity(res).build();
	    }
	    return Response.status(201).entity(res).build();
	    // String result="";
	    // 

	}
	
	/**
	 * View gradecard
	 * 
	 */
	@POST
	@Path("/viewGradeCard")
	@Consumes("application/json")
	@Produces(MediaType.TEXT_HTML)
	public Response ViewGradeCard (String t){
		logger.info("ViewGradeCard method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}
	    GradeCardInterface gradeCardOperation = GradeCardService.getInstance();
	    
	    UserService.currentUsedId=map.get("userID");
	    StudentService.current_semester=Integer.parseInt(map.get("current_semester"));

	    String res=gradeCardOperation.viewGradeCard(UserService.currentUsedId, StudentService.current_semester);
	    
	    if(res==null) {
	    	res="Cannot view grade card";
		     return Response.status(409).entity(res).build();
	    }
	    return Response.status(201).entity(res).build();
	}
	
	/**
	 * Check payment 
	 * 
	 */
	@POST
	@Path("/paymentCheck")
	@Consumes("application/json")
	@Produces(MediaType.TEXT_HTML)
	public Response PaymentCheck (String t) {
		logger.info("PaymentCheck method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}

	    PaymentInterface paymentOperation = new PaymentService();

	        UserService.currentUsedId=map.get("userID");
	        StudentService.current_semester=Integer.parseInt(map.get("current_semester"));

	        boolean res=paymentOperation.checkForPayment(UserService.currentUsedId);
	       // System.out.println("hello");
	        
	        if(res) {
			    return Response.status(201).entity("redirecting to payment portal").build();
	        }
	        else {
	         return Response.status(201).entity("You don't have any pending payments").build();
	        }
	    // gradeCardOperation.viewGradeCard(UserService.currentUsedId, StudentService.current_semester);

	}

	/**
	 * View Payment via card
	 * 
	 */
	@POST
	@Path("/paymentViaCard")
	@Consumes("application/json")
	@Produces(MediaType.TEXT_HTML)
	public Response PaymentViaCard (String t) {
		logger.info("PaymentViaCard method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}

	    PaymentInterface paymentOperation = new PaymentService();

	        UserService.currentUsedId=map.get("userID");
	        StudentService.current_semester=Integer.parseInt(map.get("current_semester"));
	        
	            String cardNumber = map.get("cardNumber");
	            CardType cardType = (map.get("cardType").equals("Debit") ? CardType.DEBIT : CardType.CREDIT);
	            int month = Integer.parseInt(map.get("expiry_month"));
	            int year = Integer.parseInt(map.get("expiry_year"));
	            int cvv = Integer.parseInt(map.get("cvv"));
	            String bankName = map.get("bank_name");
	    
	            Card card = new Card(cardNumber, cardType, month, year, bankName);
	            if(paymentOperation.makePaymentByCard(card)) {
				    return Response.status(201).entity("Payment Successful").build();
				}
				else {
				     return Response.status(409).entity("Payment UnSuccessful").build();
				}
	    
	    // gradeCardOperation.viewGradeCard(UserService.currentUsedId, StudentService.current_semester);

	}
	
	/**
	 * View payment via netbanking
	 * 
	 */
	@POST
	@Path("/paymentViaNetbanking")
	@Consumes("application/json")
	@Produces(MediaType.TEXT_HTML)
	public Response PaymentViaNetbanking (String t) {
		logger.info("PaymentViaNetbanking method started");

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(t, new TypeReference<HashMap<String, String>>() {
			});
		} catch (Exception e) {
			return Response.status(409).entity("Registration failed, Try again!").build();
		}

	    PaymentInterface paymentOperation = new PaymentService();

	        UserService.currentUsedId=map.get("userID");
	        StudentService.current_semester=Integer.parseInt(map.get("current_semester"));

	            if(paymentOperation.makePaymentByNetBanking()) {
				    return Response.status(201).entity("Payment Successful").build();
				}
				else {
				     return Response.status(409).entity("Payment UnSuccessful").build();
				}  
	    // gradeCardOperation.viewGradeCard(UserService.currentUsedId, StudentService.current_semester);

	}
	
	/**
	 * delete customer
	 * 
	 */
	@DELETE
	@Path("/delete/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") int customerId)
	throws URIReferenceException{
		logger.info("deleteCustomer method started");

		// service query to perfomr the delete operation
		
		// implementation
		return Response.status(200).entity("Track id " +customerId +
				"successfully deleted").build();
		
	
	}
	
	

}