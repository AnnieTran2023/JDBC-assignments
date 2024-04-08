package exercise5;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentUpdateProgramUsingDAO {

	public static void main(String[] args) {
		
		System.out.println("=== Update student ===");
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Id: ");
		int id = Integer.parseInt(input.nextLine());
		
		System.out.print("First name: ");
		String firstname = input.nextLine();
		
		System.out.print("Last name: ");
		String lastname = input.nextLine();
		
		System.out.print("Street: ");
		String streetaddress = input.nextLine();
		
		System.out.print("Postcode: ");
		int postcode = Integer.parseInt(input.nextLine());
		
		System.out.print("Post office: ");
		String postoffice = input.nextLine();
		
		Student student = new Student(id, firstname, lastname, streetaddress, postcode, postoffice);
		
		StudentDAO updatedStudent = new StudentDAO();
		
		int result = updatedStudent.updateStudent(student);
		
		if (result == 0) {
			System.out.println("\nStudent data updated!");
		} else if (result == 1) {
			System.out.println(String.format("\nNothing updated. Unknown student id (%d)!", id));
		}
		
	}

}
