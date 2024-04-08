package exercise2;

import java.sql.*;

import data_access.ConnectionParameters;
import data_access.DbUtils;

public class SimpleStudentListProgram {

	public static void main(String[] args) {

		
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		ResultSet resultSet = null; 
		
		System.out.println("=== Print students ===");
		
		try {
			
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);
			
			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student ORDER BY id DESC";
			
			preparedStatement = connection.prepareStatement(sqlText);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				int postcode = resultSet.getInt("postcode");
				String postoffice = resultSet.getString("postoffice");
				
				System.out.println(String.format("%d: %s %s, %s, %d %s", id, firstname, lastname, streetaddress, postcode, postoffice));
			}
			
		} catch (SQLException sqle) {
			
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			
		} finally {
			
			
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
	}

}
