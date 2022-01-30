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
	public static final String insertPaymentQuery = "INSERT INTO `CRS`.`payment` (`studentId`, `invoiceId`, `amount`, `status`) VALUES (?, ?, ?, ?);";
	public static final String updatePaymentQuery = "UPDATE CRS.payment SET status=?, type=?, Date=? where (studentid=?)";
	public static final String insertPaymentNotifierQuery= "INSERT INTO `CRS`.`paymentNotifier` (`studentId`, `invoiceId`, `message`) VALUES (?, ?, ?);";
	public static final String addCoursesToDb="INSERT INTO `CRS`.`course` (`courseId`, `name`, `duration`, `credits`, `semester`) VALUES (?, ?, ?, ?, ?)";
	public static final String deleteCourseFromDb="delete from CRS.course where name= ? ";
	public static final String fetchRegisteredCourseFromStudentId = "select * from CRS.registeredCourse where studentID = ?  AND semester = ?";
	public static final String fetchGradeCardUsingStudentId = "select * from CRS.gradecard where studentId = ? AND semester = ?";
	public static final String gradeCardGenQuery="insert into CRS.gradecard(gradeCardId, studentId, semester, grade) values (?, ?, ?, ?)";
	public static final String printEnrolledStudentInThatCourseQuery="select studentId from CRS.registeredCourse where courseId = ?";
	public static final String generateGradeCardBySemQuery="Select studentId, sum(grade)/4 as SGPA from CRS.registeredCourse where semester = ? group by studentId";
	public static final String dropCourseQuery="delete from CRS.registeredCourse where courseId=? and studentId=?";
	public static final String updateGrade="update CRS.registeredCourse set grade=? where courseId= ? and studentId=? ";
	public static final String GET_ALL_PAYMENT_FOR_STUDENT="select * from crs.payment where studentId = ? and status = 0";
	public static final String fetchCourseFromId="select * from CRS.course where courseId = ?";
	public static final String fetchStudentNameFromId="select name from CRS.student where studentId=?";
}
