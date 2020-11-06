package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Paciente;
//import net.javaguides.usermanagement.model.User;

public class PacienteDAO {
	
	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String INSERT_PACIENTE_SQL = "INSERT INTO pacientes" + "  (cpf, nome, prontuario ) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_PACIENTE_BY_ID = "select id,cpf,nome,prontuario,dataDeEntrada from pacientes where id =?";
	private static final String SELECT_ALL_PACIENTES = "select * from pacientes";
	private static final String DELETE_PACIENTE_SQL = "delete from pacientes where id = ?;";
	private static final String UPDATE_PACIENTE_SQL = "update pacientes set cpf = ?,nome = ?,prontuario= ?, dataDeEntrada =? where id = ?;";

	public PacienteDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
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
	
	public void insertPaciente(Paciente paciente) throws SQLException {
		System.out.println(INSERT_PACIENTE_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTE_SQL)) {
			preparedStatement.setString(1, paciente.getCpf());
			preparedStatement.setString(2, paciente.getNome());
			preparedStatement.setString(3, paciente.getProntuario());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Paciente selectPaciente(int id) {
		Paciente paciente = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PACIENTE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String prontuario = rs.getString("prontuario");
				String dataDeEntrada = rs.getString("dataDeEntrada");
				paciente = new Paciente(id, cpf, nome, prontuario, dataDeEntrada);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return paciente;
	}

	public List<Paciente> selectAllPacientes() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Paciente> pacientes = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PACIENTES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String prontuario = rs.getString("prontuario");
				String dataDeEntrada = rs.getString("dataDeEntrada");
				pacientes.add(new Paciente(id, cpf, nome, prontuario, dataDeEntrada));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pacientes;
	}
	
	public boolean deletePaciente(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PACIENTE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean updatePaciente(Paciente paciente) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PACIENTE_SQL);) {
			
			statement.setString(1, paciente.getCpf());
			statement.setString(2, paciente.getNome());
			statement.setString(3, paciente.getProntuario());
			statement.setString(4, paciente.getDataDeEntrada());
			statement.setInt(5, paciente.getId());

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
