package data_access;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Student;

public class StudentDAO {

	public StudentDAO() {
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}

	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);
	}

	public List<Student> getAllStudents() {

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultSet = null;

		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();
			String sql = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice from Student";
			preparedstatement = connection.prepareStatement(sql);
			resultSet = preparedstatement.executeQuery();

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				int postcode = resultSet.getInt("postcode");
				String postoffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedstatement, connection);
		}

		return studentList;

	}

	public void getStudentbyId(int searchID) {

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultSet = null;

		try {
			connection = openConnection();
			String sql = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice from Student where id = ?";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, searchID);
			resultSet = preparedstatement.executeQuery();

			if (resultSet.next()) {

				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				int postcode = resultSet.getInt("postcode");
				String postoffice = resultSet.getString("postoffice");

				System.out.println(String.format("%d, %s %s, %s, %d %s", id, firstname, lastname, streetaddress,
						postcode, postoffice));

			} else {
				System.out.println(String.format("Unknown student id (%d)", searchID));
			}
		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");

		} finally {
			DbUtils.closeQuietly(resultSet, preparedstatement, connection);
		}

	}

	public String getAllStudentsJSON() {

		StudentDAO newList = new StudentDAO();

		List<Student> studentList = newList.getAllStudents();

		Gson gson = new Gson();
		String jsonString = gson.toJson(studentList);

		return jsonString;
	}

	public int insertStudent(Student student) {

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultSet = null;

		try {

			connection = openConnection();
			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ? ,? ,? ,?)";
			preparedstatement = connection.prepareStatement(sqlText);

			preparedstatement.setInt(1, student.getId());
			preparedstatement.setString(2, student.getFirstname());
			preparedstatement.setString(3, student.getLastname());
			preparedstatement.setString(4, student.getStreetaddress());
			preparedstatement.setInt(5, student.getPostcode());
			preparedstatement.setString(6, student.getPostoffice());

			preparedstatement.executeUpdate();

			return 0;

		} catch (SQLException sqle) {

			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				return 1;

			} else {

				return -1;
			}

		} finally {

			DbUtils.closeQuietly(resultSet, preparedstatement, connection);

		}
	}

	public int deleteStudent(int studentId) {

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultSet = null;

		try {

			connection = openConnection();

			String sqlText = "DELETE FROM Student WHERE id = ?";

			preparedstatement = connection.prepareStatement(sqlText);

			preparedstatement.setInt(1, studentId);

			if (preparedstatement.executeUpdate() > 0) {
				return 0;

			} else {
				return 1;
			}

		} catch (SQLException sqle) {

			return -1;

		} finally {

			DbUtils.closeQuietly(resultSet, preparedstatement, connection);

		}
	}

	public int updateStudent(Student student) {

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultSet = null;

		try {

			connection = openConnection();

			String sql = "UPDATE Student SET firstname = ?, lastname = ?, streetaddress = ?, postcode = ?, postoffice =? WHERE id = ?";

			preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setString(1, student.getFirstname());
			preparedstatement.setString(2, student.getLastname());
			preparedstatement.setString(3, student.getStreetaddress());
			preparedstatement.setInt(4, student.getPostcode());
			preparedstatement.setString(5, student.getPostoffice());
			preparedstatement.setInt(6, student.getId());


			preparedstatement.executeUpdate();

			if (preparedstatement.executeUpdate() > 0) {
				return 0;

			} else {
				
				return 1;
			}

		} catch (SQLException sqle) {

			return -1;

		} finally {

			DbUtils.closeQuietly(resultSet, preparedstatement, connection);

		}
	}
}
