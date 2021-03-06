/**

 * 
 */
package com.crs.flipkart.bean;

/**
 * @author nikhil
 *
 */
public class GradeCard {
	private String gradeCardId;
	private String studentId;//fk
	private int semester;
	private float grade;
	private RegisteredCourse RegisteredCourse[] = new RegisteredCourse[] {};
	
	/**
	 * @return the gradeCardId
	 */
	public String getGradeCardId() {
		return gradeCardId;
	}



	/**
	 * @param gradeCardId the gradeCardId to set
	 */
	public void setGradeCardId(String gradeCardId) {
		gradeCardId = gradeCardId;
	}
	/**
	 * @return the registeredCourse
	 */
	public RegisteredCourse[] getRegisteredCourse() {
		return RegisteredCourse;
	}



	/**
	 * @param registeredCourse the registeredCourse to set
	 */
	public void setRegisteredCourse(RegisteredCourse[] registeredCourse) {
		RegisteredCourse = registeredCourse;
	}



	/**
	 * @return the grade
	 */
	public float getGrade() {
		return grade;
	}



	/**
	 * @param grade the grade to set
	 */
	public void setGrade(float grade) {
		this.grade = grade;
	}


	
	
	/**
	 * @return the studentID
	 */
	public String getStudentID() {
		return studentId;
	}



	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(String studentID) {
		this.studentId = studentID;
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

}
