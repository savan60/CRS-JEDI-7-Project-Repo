/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.exceptions.CheckForSemesterRegistration;

/**
 * @author SAVAN
 *
 */
public interface SemesterRegistrationDaoInterface {
	public boolean checkSemAndStudentIdExists(int sem,String studentId) throws CheckForSemesterRegistration;
	
	public boolean insertSem(int sem,String studentId);
}
