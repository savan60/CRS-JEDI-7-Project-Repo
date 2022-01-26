package com.crs.flipkart.bean;

/**
 * @author nandini 
 */

class Date{
	private int day;
	private int month;
	private int year;
	
	Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
}

public class SemesterRegistration {
	String SemesterRegistrationId;//pk
	String StudentID;//fk
	int Semester;
	Date date;
	
	SemesterRegistration(String StudentID, int Semester, Date date){
		this.StudentID = StudentID;
		this.Semester = Semester;
		this.date = date;
	}
	
	/**
	 * @param StudentId the StudentID to set
	 */
	public void setStudentID(String StudentID) {
		this.StudentID = StudentID;
	}
	
	/**
	 * @param Semester the Semester to set
	 */
	public void setSemester(int Semester) {
		this.Semester = Semester;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return the StudentID
	 */
	public String getStudentID() {
		return this.StudentID;
	}
	
	/**
	 * @return the Semester
	 */
	public int getSemester(int Semester) {
		return this.Semester;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}
	
}
