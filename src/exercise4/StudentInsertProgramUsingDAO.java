package exercise4;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentInsertProgramUsingDAO {

	public static void main(String[] args) {
		
		System.out.println("=== Insert Student Using DAO===");
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter student id: ");
		int id = Integer.parseInt(input.nextLine());
		
		System.out.print("Enter first name: ");
		String firstname = input.nextLine();
		
		System.out.print("Enter last name: ");
		String lastname = input.nextLine();
		
		System.out.print("Enter street address: ");
		String streetaddress = input.nextLine();
		
		System.out.print("Enter postcode: ");
		int postcode = Integer.parseInt(input.nextLine());
		
		System.out.print("Enter post office: ");
		String postoffice = input.nextLine();
		
		Student newStudent = new Student(id, firstname, lastname, streetaddress, postcode, postoffice);
		
		StudentDAO added = new StudentDAO();
		
		System.out.println(added.insertStudent(newStudent));
		
	}

}
