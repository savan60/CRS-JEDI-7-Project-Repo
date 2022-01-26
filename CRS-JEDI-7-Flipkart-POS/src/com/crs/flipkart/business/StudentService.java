package com.crs.flipkart.business;

import com.crs.flipkart.dao.SemesterRegistrationDaoInterface;
import com.crs.flipkart.dao.SemesterRegistrationDaoOperation;

public class StudentService implements StudentInterface{
	SemesterRegistrationDaoInterface semesterRegistration=new SemesterRegistrationDaoOperation();
	public void selfRegistration() {
		
	}
	public void viewGradeCard() {
		
	}
	public void viewCatalogue() {
		
	}
	
	public boolean semesterRegistration(int sem) {
		boolean val=semesterRegistration.checkSemAndStudentIdExists(sem, UserService.currentUsedId);
		if(!val) {
			boolean res=semesterRegistration.insertSem(sem, UserService.currentUsedId);
			if(res) {
				System.out.println("Semester Registration Successful");
				return true;
			}
			else {
				System.out.println("Semseter Registration Failed, Try again!");
			}
		}
		else {
			System.out.println("Registration of the semester is already done");
		}
		return false;
	}
	
}
