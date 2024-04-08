package exercise3;

import java.util.Scanner;

import data_access.StudentDAO;

public class StudentSearchProgramUsingDAO {

	public static void main(String[] args) {
		
		System.out.println("=== Find student by id (DAO version) ===");
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter student id: ");
		int searchID = Integer.parseInt(input.nextLine());
		
		StudentDAO searchedStudent = new StudentDAO();
		
		searchedStudent.getStudentbyId(searchID);
		
	}

}
