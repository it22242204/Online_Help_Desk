package helpdesk.ticket;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 *
 */
public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/helpdesk?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Mathu1712";

	private static final String INSERT_DETAIL_SQL = "INSERT INTO detail (Name, Email,address) VALUES (?, ?, ?)";

	private static final String SELECT_DETAIL_BY_ID = "select ID,Name, Email,address from detail where ID =?";
	private static final String SELECT_ALL_DETAIL = "select * from detail";
	private static final String DELETE_DETAIL_SQL = "delete from detail where ID = ?";
	private static final String UPDATE_DETAIL_SQL = "update detail set Name = ?,Email= ?, address =? where ID = ?";

	public UserDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(user user)throws SQLException  {
		System.out.println(INSERT_DETAIL_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DETAIL_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getaddress());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public user selectUser(int ID) {
		user user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DETAIL_BY_ID)) {
			preparedStatement.setInt(1, ID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String Name = rs.getString("Name");
				String Email = rs.getString("Email");
				String address = rs.getString("address");
				
				user = new user(ID, Name, Email,address);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<user> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<user> detail = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DETAIL);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String Name = rs.getString("Name");
				String Email = rs.getString("Email");
				String address = rs.getString("address");
			
				detail.add(new user(ID, Name, Email,address));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return detail;
	}

	public boolean deleteUser(int ID)throws SQLException {
		boolean rowDeleted ;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DETAIL_SQL);) {
			statement.setInt(1, ID);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(user user)throws SQLException{
		boolean rowUpdated ;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_DETAIL_SQL);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getaddress());
			statement.setInt(4, user.getID());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	


}
