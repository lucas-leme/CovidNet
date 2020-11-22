package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Prontuario;
//import net.javaguides.usermanagement.model.User;

public class ProntuarioDAO {

	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String INSERT_PRONTUARIO_SQL = "INSERT INTO pacientes" + "  (cpf, nome, data_de_nascimento) VALUES "
			+ " (?, ?, ?, ?);";
	
	private static final String SELECT_ALL_PRONTUARIOS = 
			"select pac.id, pac.cpf, pac.nome, pac.data_de_nascimento, pac.data_de_entrada, pro.nome_exame, pro.descricao_exame, pro.data, pro.resultado"
			+ "		from pacientes pac"
			+ "			inner join "
			+ "				prontuarios pro on pro.paciente_id = pac.id";
	
	private static final String SELECT_PRONTUARIO_BY_ID =
			"select pac.id, pac.cpf, pac.nome, pac.data_de_nascimento, pac.data_de_entrada, pro.nome_exame, pro.descricao_exame, pro.data, pro.resultado"
			+ "		from pacientes pac"
			+ "			inner join "
			+ "				prontuarios pro on pro.paciente_id = pac.id"
			+ "				where pac.id = ?";
	
	private static final String UPDATE_PRONTUARIO_SQL = "update pacientes set cpf = ?,nome = ?, data_de_nascimento =?,data_de_entrada=? where id = ?;";

	public ProntuarioDAO() {
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
	
	public void insertProntuario(Prontuario prontuario) throws SQLException {
		System.out.println(INSERT_PRONTUARIO_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRONTUARIO_SQL)) {
			preparedStatement.setString(1, prontuario.getCpf());
			preparedStatement.setString(2, prontuario.getNome());
			preparedStatement.setString(3, prontuario.getDataDeNascimento());
			preparedStatement.setString(4, prontuario.getNomeDoExame());
			preparedStatement.setString(5, prontuario.getDescricaoExame());
			preparedStatement.setString(6, prontuario.getDataExame());
			preparedStatement.setString(7, prontuario.getResultadoExame());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Prontuario selectProntuario(int id) {
		Prontuario prontuario = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRONTUARIO_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String data_de_nascimento = rs.getString("data_de_nascimento");
				String data_de_entrada = rs.getString("data_de_entrada");
				String nome_exame = rs.getString("nome_exame");
				String descricao_exame = rs.getString("descricao_exame");
				String data = rs.getString("data");
				String resultado = rs.getString("resultado");
				
				prontuario = new Prontuario(id, cpf, nome, data_de_nascimento, data_de_entrada, nome_exame, descricao_exame, data, resultado);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prontuario;
	}

	public List<Prontuario> selectAllProntuarios() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Prontuario> prontuarios = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRONTUARIOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String data_de_nascimento = rs.getString("data_de_nascimento");
				String data_de_entrada = rs.getString("data_de_entrada");
				String nome_exame = rs.getString("nome_exame");
				String descricao_exame = rs.getString("descricao_exame");
				String data = rs.getString("data");
				String resultado = rs.getString("resultado");
				prontuarios.add(new Prontuario(id, cpf, nome, data_de_nascimento, data_de_entrada, nome_exame, descricao_exame, data, resultado));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prontuarios;
	}

	public boolean updateProntuario(Prontuario prontuario) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRONTUARIO_SQL);) {
			
			statement.setString(1, prontuario.getCpf());
			statement.setString(2, prontuario.getNome());
			statement.setString(3, prontuario.getDataDeNascimento());
			statement.setString(4, prontuario.getDataDeEntrada());
			statement.setString(5, prontuario.getNomeDoExame());
			statement.setString(6, prontuario.getDescricaoExame());
			statement.setString(7, prontuario.getDataExame());
			statement.setString(8, prontuario.getResultadoExame());
			statement.setInt(9, prontuario.getId());
	
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
