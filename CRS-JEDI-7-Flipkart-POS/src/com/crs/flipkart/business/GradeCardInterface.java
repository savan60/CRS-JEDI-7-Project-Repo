/**
 * 
 */
package com.crs.flipkart.business;
import com.crs.flipkart.bean.RegisteredCourse;

import java.util.ArrayList;
/**
 * @author SAVAN
 *
 */
public interface GradeCardInterface {
	public float calGrade(ArrayList<RegisteredCourse> courses);

}
