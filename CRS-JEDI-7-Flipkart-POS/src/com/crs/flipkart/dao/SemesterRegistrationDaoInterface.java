/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author SAVAN
 *
 */
public interface SemesterRegistrationDaoInterface {
	public boolean checkSemAndStudentIdExists(int sem,String studentId);
	
	public boolean insertSem(int sem,String studentId);
}
