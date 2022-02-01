/**
 * 
 */
package com.crs.flipkart.dao;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.exceptions.NoStudentForApprovalException;
import com.crs.flipkart.exceptions.UserAlreadyExistsException;

/**
 * @author SAVAN
 *
 */
public interface AdminDaoInterface {
	
	public boolean addProfessorToDB(Professor professor) throws UserAlreadyExistsException;
	public boolean approveStudents(String id) throws NoStudentForApprovalException;
	
}
