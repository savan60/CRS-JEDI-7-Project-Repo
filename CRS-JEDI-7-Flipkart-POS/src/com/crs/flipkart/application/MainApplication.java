/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

//import 
/**
 * @author SAVAN
 *
 */
public class MainApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ProfessorApplication professor=new ProfessorApplication();
		
		while(true) {
		System.out.println("Select your role\n"+"1. Admin\n"+"2. Professor\n"+"3. Student\n4. Exit");
		Scanner sc=new Scanner(System.in);
		int ch=sc.nextInt();
		
		switch(ch) {
			case 1: System.out.println("Admin");
			     break;
			case 2: professor.entry();
				break;
			case 3:System.out.println("Student");
		     	break;
			case 4: System.exit(0);
			default: System.out.println("Invalid choice");
		}
		
		}

	}

}
