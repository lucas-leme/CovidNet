package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.RelatorioMunicipal;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class RelatorioMunicipalDAO {
	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	//private String jdbcURL = "jdbc:mysql://localhost:3306/test?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String INSERT_RELATORIOS_SQL = "INSERT INTO municipais" + " (nume_municipio, num_hospitais) VALUES " + " (?, ?);";
	private static final String SELECT_RELATORIO_BY_ID = "select relatorio_id, num_municipios, num_hospitais from municipais where relatorio_id =?";
	private static final String SELECT_ALL_RELATORIOS = "select * from municipais";
	private static final String DELETE_RELATORIO_SQL = "delete from estaduais where relatorio_id = ?;";
	private static final String UPDATE_RELATORIO_SQL = "update municipais set num_municipios = ?, num_hospitais = ? where relatorio_id = ?;";

	public RelatorioMunicipalDAO() {
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

	public void insertRelatorio(RelatorioMunicipal relatorio) throws SQLException {
		System.out.println(INSERT_RELATORIOS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RELATORIOS_SQL)) {
			preparedStatement.setString(1, relatorio.getNomeMunicipio());
			preparedStatement.setFloat(2, relatorio.getNumeroHospitais());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public RelatorioMunicipal selectRelatorio(int id) {
		RelatorioMunicipal relatorio = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RELATORIO_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println("AQUIAQUI");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("RS");
			System.out.println(rs);

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nomeMunicipio = rs.getString("nome_municipio");
				int numeroHospitais = rs.getInt("num_hospitais");
				relatorio = new RelatorioMunicipal(id, nomeMunicipio, numeroHospitais);
				System.out.println("AQUI");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}

	public List<RelatorioMunicipal> selectAllRelatorios() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<RelatorioMunicipal> relatorios = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RELATORIOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				
				int id = rs.getInt("relatorio_id");
				String nomeMunicipio = rs.getString("nome_municipio");
				int numeroHospitais = rs.getInt("num_hospitais");
				relatorios.add(new RelatorioMunicipal(id, nomeMunicipio, numeroHospitais));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorios;
	}

	public boolean deleteRelatorio(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_RELATORIO_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateRelatorio(RelatorioMunicipal relatorio) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RELATORIO_SQL);) {

			statement.setString(1, relatorio.getNomeMunicipio());
			statement.setInt(2, relatorio.getNumeroHospitais());
			statement.setInt(3, relatorio.getId());
			
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
