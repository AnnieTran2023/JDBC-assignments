package exercise2;

import java.sql.*;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;

public class SimpleStudentInsertProgram {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Scanner input = new Scanner(System.in);
		System.out.println("=== Add student ===");

		System.out.print("\nId: ");
		int id = Integer.parseInt(input.nextLine());

		System.out.print("First name: ");
		String firstname = input.nextLine();

		System.out.print("Last name: ");
		String lastname = input.nextLine();

		System.out.print("Street: ");
		String streetaddress = input.nextLine();

		System.out.print("Postcode: ");
		int postcode = Integer.parseInt(input.nextLine());

		System.out.print("Postoffice: ");
		String postoffice = input.nextLine();

		try {
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ? ,? ,? ,?)";
			preparedStatement = connection.prepareStatement(sqlText);

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, firstname);
			preparedStatement.setString(3, lastname);
			preparedStatement.setString(4, streetaddress);
			preparedStatement.setInt(5, postcode);
			preparedStatement.setString(6, postoffice);

			preparedStatement.executeUpdate();

			System.out.println("\nStudent data added!");

		} catch (SQLException sqle) {

			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				System.out.println("\nCannot insert the student. Student ID ( " + id + " ) is already in use.");

			} else {

				System.out.println(

						"The database is temporarily unavailable. Please try again later " + sqle.getMessage());
			}

		} finally {

			DbUtils.closeQuietly(resultSet, preparedStatement, connection);

		}
	}

}
