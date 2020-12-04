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
 * 
 * @author Hugo Martins
 *
 */

public class LeitoDAO {
	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String SELECT_ALL_LEITOS =
			"SELECT l.id id, l.ocupado, p.nome paciente, h.nome hospital\n"
			+ "FROM leitos l\n"
			+ "		INNER JOIN pacientes p\n"
			+ "		ON p.id = l.paciente_id\n"
			+ "		INNER JOIN hospitais h\n"
			+ "		ON h.id = l.hospital_id\n";

	private static final String SELECT_LEITO_BY_ID = SELECT_ALL_LEITOS + "WHERE l.id = ?";
	
	private static final String SELECT_LEITO_DISPONIVEL = 
			"SELECT * FROM leitos"
			+ " WHERE hospital_id = ? AND ocupado = 0"
			+ " LIMIT 1 ";

	private static final String SELECT_LEITO_BY_PACIENTE_ID = SELECT_ALL_LEITOS + "WHERE l.paciente_id = ?";
	
	private static final String SELECT_LEITOS_BY_HOSPITAL_ID = SELECT_ALL_LEITOS + "WHERE l.hospital_id = ?";
	
	private static final String SELECT_LEITOS_DESOCUPADOS_BY_HOSPITAL_ID = SELECT_ALL_LEITOS + "WHERE l.hospital_id = ? AND l.ocupado = 0";
	
	private static final String OCUPA_LEITO = "UPDATE leitos SET ocupado = 1, paciente_id = ?  WHERE id = ?";

	private static final String DESOCUPA_LEITO = "UPDATE leitos SET ocupado = 0, paciente_id = null  WHERE id = ?";

	private static final String DESOCUPA_LEITO_BY_PACIENTE_ID = "UPDATE leitos SET ocupado = 0, paciente_id = null  WHERE paciente_id = ?";

	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public Leito selectLeitoById(int leito_id) {
		
		Leito leito = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LEITO_BY_ID);) {
			preparedStatement.setInt(1, leito_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			int id = rs.getInt("id");
			boolean ocupado = rs.getBoolean("ocupado");
			String paciente = rs.getString("paciente");
			String hospital = rs.getString("hospital");
			leito = new Leito(id, ocupado, paciente, hospital);

		} catch (SQLException e) {
			printSQLException(e);
		}
		return leito;
	}
	
	public Leito selectLeitoByPacienteId(int paciente_id) {
		
		Leito leito = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LEITO_BY_PACIENTE_ID);) {
			preparedStatement.setInt(1, paciente_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			int id = rs.getInt("id");
			boolean ocupado = rs.getBoolean("ocupado");
			String paciente = rs.getString("paciente");
			String hospital = rs.getString("hospital");
			leito = new Leito(id, ocupado, paciente, hospital);

		} catch (SQLException e) {
			printSQLException(e);
		}
		return leito;
	}
	
public List<Leito> selectLeitosByHospital(int hospital_id) {
		
		List<Leito> leitos = new ArrayList<>();
		
		try (
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LEITOS_BY_HOSPITAL_ID);) {
				preparedStatement.setInt(1, hospital_id);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
	
				while (rs.next()) {
					int id = rs.getInt("id");
					boolean ocupado = rs.getBoolean("ocupado");
					String paciente = rs.getString("paciente");
					String hospital = rs.getString("hospital");
					leitos.add(new Leito(id, ocupado, paciente, hospital));
				}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return leitos;
	}

public List<Leito> selectLeitosDesocupadosByHospital(int hospital_id) {
	
	List<Leito> leitos = new ArrayList<>();
	
	try (
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LEITOS_DESOCUPADOS_BY_HOSPITAL_ID);) {
			preparedStatement.setInt(1, hospital_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				boolean ocupado = rs.getBoolean("ocupado");
				String paciente = rs.getString("paciente");
				String hospital = rs.getString("hospital");
				leitos.add(new Leito(id, ocupado, paciente, hospital));
			}
	} catch (SQLException e) {
		printSQLException(e);
	}
	return leitos;
}

	public List<Leito> selectAllLeitos() {
		
		List<Leito> leitos = new ArrayList<>();
		
		try (
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LEITOS);) 
		{
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
	
				while (rs.next()) {
					int id = rs.getInt("id");
					boolean ocupado = rs.getBoolean("ocupado");
					String paciente = rs.getString("paciente");
					String hospital = rs.getString("hospital");
					leitos.add(new Leito(id, ocupado, paciente, hospital));
				}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return leitos;
	}

	public boolean ocupaLeito(int leito_id, int paciente_id) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(OCUPA_LEITO);) {
			statement.setInt(1, paciente_id);
			statement.setInt(2, leito_id);

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
public int ocupaLeitoAleatorio(int hospital_id, int paciente_id) throws SQLException {
		
		Leito leito = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LEITO_DISPONIVEL);) {
			preparedStatement.setInt(1, hospital_id);
			System.out.println("ALEATORIO");
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.first();

			int id = rs.getInt("id");
			boolean ocupado = rs.getBoolean("ocupado");
			int paciente = rs.getInt("paciente_id");
			int hospital = rs.getInt("hospital_id");
			leito = new Leito(id, ocupado, paciente, hospital);

		} catch (SQLException e) {
			printSQLException(e);
		}
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(OCUPA_LEITO);) {
			
			statement.setInt(1, paciente_id);
			statement.setInt(2, leito.getId());
			
			System.out.println("OCUPANDO LEITO");
			System.out.println(statement);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return leito.getId();
	}
	
	public boolean desocupaLeito(int leito_id) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DESOCUPA_LEITO);) {
			statement.setInt(1, leito_id);

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean desocupaLeitoByPacienteId(int paciente_id) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DESOCUPA_LEITO_BY_PACIENTE_ID);) {
			statement.setInt(1, paciente_id);

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
