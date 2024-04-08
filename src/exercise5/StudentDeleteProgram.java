package exercise5;

import java.util.Scanner;

import data_access.StudentDAO;

public class StudentDeleteProgram {

	public static void main(String[] args) {
		
		System.out.println("===Delete Student Using DAO===");

		Scanner input = new Scanner(System.in);

		System.out.print("Enter student ID: ");
		int deleteID = Integer.parseInt(input.nextLine());

		StudentDAO inputstudent = new StudentDAO();
		int result = inputstudent.deleteStudent(deleteID);
		System.out.println(result);
	}

}
