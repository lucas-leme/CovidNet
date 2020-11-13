package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Leito;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class LeitoDAO {
	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	//private String jdbcURL = "jdbc:mysql://localhost:3306/test?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String INSERT_LEITOS_SQL = "INSERT INTO leitos" + "  (medico, enfermeiro, paciente) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_LEITO_BY_ID = "select id,medico,enfermeiro,paciente from leitos where id =?";
	private static final String SELECT_ALL_LEITOS = "select * from leitos";
	private static final String DELETE_LEITOS_SQL = "delete from leitos where id = ?;";
	private static final String UPDATE_LEITOS_SQL = "update leitos set medico = ?,enfermeiro= ?, paciente =? where id = ?;";

	public LeitoDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		
		System.out.println("getting connection (DAO)");
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
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

	public void insertLeito(Leito leito) throws SQLException {
		
		System.out.println("inserting in leito (DAO)");
		System.out.println(INSERT_LEITOS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LEITOS_SQL)) {
			preparedStatement.setString(1, leito.getMedico());
			preparedStatement.setString(2, leito.getEnfermeiro());
			preparedStatement.setString(3, leito.getPaciente());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Leito selectLeito(int id) {
		
		System.out.println("selecting leito (DAO)");
		Leito leito = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LEITO_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String medico = rs.getString("medico");
				String enfermeiro = rs.getString("enfermeiro");
				String paciente = rs.getString("paciente");
				leito = new Leito(id, medico, enfermeiro, paciente);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return leito;
	}

	public List<Leito> selectAllLeitos() {
		
		System.out.println("\nselecting all leitos (DAO)");

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Leito> leitos = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (
			Connection connection = getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LEITOS);) 
		{
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
	
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String medico = rs.getString("medico");
					String enfermeiro = rs.getString("enfermeiro");
					String paciente = rs.getString("paciente");
					leitos.add(new Leito(id, medico, enfermeiro, paciente));
				}
		} catch (SQLException e) {
			System.out.println("SQL exception");
			printSQLException(e);
		}
		System.out.println("Leitos pegos: " + leitos.toString());
		
		return leitos;
	}

	public boolean deleteLeito(int id) throws SQLException {
		
		System.out.println("deleting leito (DAO)");
		
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LEITOS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateLeito(Leito leito) throws SQLException {
		
		System.out.println("updating leito (DAO)");
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LEITOS_SQL);) {
			statement.setString(1, leito.getMedico());
			statement.setString(2, leito.getEnfermeiro());
			statement.setString(3, leito.getPaciente());
			statement.setInt(4, leito.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		
		System.out.println("printing sql exception (DAO)");
		
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
