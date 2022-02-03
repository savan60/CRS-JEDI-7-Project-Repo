/**
 * 
 */
package com.crs.flipkart.business;
import com.crs.flipkart.bean.RegisteredCourse;
import com.crs.flipkart.utils.ResultCard;

import java.util.ArrayList;
/**
 * @author SAVAN
 *
 */
public interface GradeCardInterface {

	
	public ArrayList<ResultCard> viewGradeCard(String studentID, int semester);

}
