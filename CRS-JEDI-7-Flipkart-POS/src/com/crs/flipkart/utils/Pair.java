/**
 * 
 */
package com.crs.flipkart.utils;

/**
 * @author ADARSH
 *
 */
public class Pair {
	private String left;
	private String right;
	/**
	 * @return the left
	 */

	public Pair(String left, String right) {
		super();
		this.left = left;
		this.right = right;
	}
	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}
	
}
