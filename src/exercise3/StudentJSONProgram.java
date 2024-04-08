package exercise3;

import data_access.StudentDAO;

public class StudentJSONProgram {

	public static void main(String[] args) {
		
		StudentDAO list = new StudentDAO();
		System.out.println(list.getAllStudentsJSON());
	}

}
