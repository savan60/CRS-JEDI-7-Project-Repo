/**
 * 
 */
package com.crs.flipkart.dao;

<<<<<<< HEAD
=======
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.exceptions.NoStudentForApprovalException;
import com.crs.flipkart.exceptions.UserAlreadyExistsException;

>>>>>>> f8450919117b688e345c85d9f213e13622aa954e
/**
 * @author SAVAN
 *
 */
public interface AdminDaoInterface {
	
<<<<<<< HEAD
	public void approveStudents(int count);
=======
	public boolean addProfessorToDB(Professor professor) throws UserAlreadyExistsException;
	public void approveStudents(int count) throws NoStudentForApprovalException;
	
>>>>>>> f8450919117b688e345c85d9f213e13622aa954e
}
