package exercise2;

import java.sql.*;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;

public class SimpleStudentDeleteProgram {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		System.out.println("=== Delete Student ===");
		System.out.print("Enter student id: ");
		int id = Integer.parseInt(input.nextLine());

		try {

			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			String sqlText = "DELETE FROM Student WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(sqlText);

			preparedStatement.setInt(1, id);

			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("\nStudent with ID " + id + " has been deleted.");

			} else {
				System.out.println("Nothing deleted. Unknown student id (" + id + ")");
			}

		} catch (SQLException sqle) {

			System.out.println("Database error! " + sqle.getMessage());

		} finally {

			DbUtils.closeQuietly(resultset, preparedStatement, connection);

		}

	}

}
