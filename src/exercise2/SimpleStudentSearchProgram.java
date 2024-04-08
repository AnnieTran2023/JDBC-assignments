package exercise2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;

public class SimpleStudentSearchProgram {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		System.out.println("=== Find student by id ===");
		System.out.print("Enter student id: ");

		int searchId = Integer.parseInt(input.nextLine());

		try {

			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);

			preparedStatement.setInt(1, searchId);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				int postcode = resultSet.getInt("postcode");
				String postoffice = resultSet.getString("postoffice");

				System.out.println(String.format("%d: %s %s, %s, %d %s", id, firstname, lastname, streetaddress,
						postcode, postoffice));

			} else {
				System.out.println("Unknown student id (" + searchId + ")");
			}
		} catch (SQLException sqle) {

			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {

			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
	}

}
