package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Exame;

public class ExameDAO {

	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String INSERT_EXAME =	 
			"INSERT INTO exames ("
			+ "		nome,"
			+ "		data,"
			+ "		descricao,"
			+ "		resultado,"
			+ "		prontuario_id"
			+ ") "
			+ "VALUES  (?, ?, ?, ?, ?)";
		
	private static final String SELECT_EXAMES_BY_PRONTUARIO_ID = "SELECT id, nome, data, descricao, resultado FROM exames WHERE prontuario_id = ?";
	
	private static final String UPDATE_EXAME = "UPDATE exames SET nome = ?, data = ?, descricao = ?, resultado = ? WHERE id = ?";		
	
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
	
	public void insertPaciente(Exame exame) throws SQLException {
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXAME)) {
			preparedStatement.setString(1, exame.getNome());
			preparedStatement.setString(2, exame.getData());
			preparedStatement.setString(3, exame.getDescricao());
			preparedStatement.setString(4, exame.getResultado());
			preparedStatement.setInt(5, exame.getProntuarioId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public List<Exame> selectExamesByProntuario(int prontuario_id) {
		List<Exame> exames = new ArrayList<>();;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXAMES_BY_PRONTUARIO_ID);) {
			preparedStatement.setInt(1, prontuario_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String data = rs.getString("data");
				String descricao = rs.getString("descricao");
				String resultado = rs.getString("resultado");
						
				exames.add(new Exame(id, nome, data, descricao, resultado, prontuario_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return exames;
	}

	public boolean updateProntuario(Exame exame) throws SQLException {
		boolean rowUpdated;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EXAME);) {
				
			statement.setString(1, exame.getNome());
			statement.setString(2, exame.getData());
			statement.setString(3, exame.getDescricao());
			statement.setString(4, exame.getResultado());
			statement.setInt(5, exame.getId());
			
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
