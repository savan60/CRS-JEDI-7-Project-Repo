/**
 * 
 */
package com.crs.flipkart.constant;

/**
 * @author ADARSH
 *
 */
public class SQLQueriesConstant {
	public static final String EnrolledStudentInThatCourseQuery="select studentId from CRS.registeredcourse where courseId = ?";
	public static final String updateGradeQuery="update CRS.registeredcourse set grade= ? where courseId=? and studentId=?";
	public static final String registeredCourseQuery="select * from CRS.registeredcourse r inner join CRS.course c on c.courseId=r.courseId where r.studentId= ? and r.semester=?";
	public static final String fetchCourseIdFromProfessorId= "select courseId from CRS.course where professorId =?";
	public static final String viewCourcesQuery="select * from CRS.course where semester =?";
	public static final String updateProfessorIdQuery="update CRS.course set professorId = ? where courseId=?";
	public static final String selectAllCoursesQuery="select * from CRS.course";
	public static final String selectAllProfessorsQuery="select * from CRS.professor";
	public static final String addCoursesToDb="INSERT INTO `CRS`.`course` (`courseId`, `name`, `duration`, `credits`, `semester`) VALUES (?, ?, ?, ?, ?)";
	public static final String deleteCourseFromDb="delete from CRS.course where courseId= ? ";
}
