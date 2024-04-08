package exercise3;

import java.util.List;

import data_access.StudentDAO;
import model.Student;

public class StudentListProgramUsingDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=== Print students (DAO version) ===");

		StudentDAO studentList = new StudentDAO();

		List<Student> students = studentList.getAllStudents();

		if (students == null) {
			System.out.println("The database is temporarily unavailable. Please try again later.");
		} else {
			for (Student student : students) {
				System.out.println(String.format("%d: %s %s, %s, %d %s", student.getId(), student.getFirstname(),
						student.getLastname(), student.getStreetaddress(), student.getPostcode(),
						student.getPostoffice()));
			}
		}
	}
}
