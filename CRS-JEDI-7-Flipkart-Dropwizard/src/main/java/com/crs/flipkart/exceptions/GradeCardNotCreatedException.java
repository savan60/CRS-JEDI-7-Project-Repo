package com.crs.flipkart.exceptions;

/**
 * @author nikhil
 * 
 * 
 * Raised when grade card is not created yet 
 */


@SuppressWarnings("serial")
public class GradeCardNotCreatedException extends Exception{
	
	private String studentId;
	
	private int semester;

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	
	/*
	 * Constructor
	 * @param studentId, semester
	 * 
	 */
	
	
	public GradeCardNotCreatedException(String studentId, int semester){
		this.studentId = studentId;
		this.semester = semester;
	}
	
	
	public String getMessage() {
		return "Grade Card for student with "+studentId+" for semester "+semester+"not created yet";
	}


}
