package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Hospital;
import net.javaguides.usermanagement.model.Municipio;

public class HospitalDAO {
	
	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";
	
	private static final String SELECT_ALL_MUNICIPIOS = "SELECT * FROM municipios";
	
	private static final String SELECT_ALL_HOSPITAIS = "SELECT * FROM hospitais";
	
	private static final String SELECT_HOSPITAIS_BY_MUNICIPIO_ID = 
			"SELECT h1.id, h2.nome, h1.leitos_disponiveis\n"
			+ "FROM (SELECT h.id, COUNT(*) as 'leitos_disponiveis'\n"
			+ "	FROM\n"
			+ "	hospitais h\n"
			+ "	JOIN leitos l\n"
			+ "	ON h.id = l.hospital_id\n"
			+ "	WHERE h.municipio_id = ? AND l.ocupado = 0\n"
			+ "	GROUP BY h.id) as h1\n"
			+ "	INNER JOIN hospitais as h2\n"
			+ "	ON h2.id = h1.id";
	
	private static final String SELECT_HOSPITAL_BY_ID = "SELECT * FROM hospitais WHERE id = ?";
	
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
	
	public List<Municipio> selectAllMunicipios() {
		
		List<Municipio> municipios = new ArrayList<>();
		
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MUNICIPIOS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				municipios.add(new Municipio(id, nome));
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return municipios;
	}
	
	public List<Hospital> selectAllHospitais() {
		
		List<Hospital> hospitais = new ArrayList<>();
		
		try (Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_HOSPITAIS);) {	
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String telefone = rs.getString("telefone");
			String endereco = rs.getString("endereco");
			String estado = rs.getString("estado");
			int municipio_id = rs.getInt("municipio_id");
			
			hospitais.add(new Hospital(id, nome, telefone, endereco, estado, municipio_id));
		}
		
		} catch (SQLException e) {
			printSQLException(e);
		}

		return hospitais;
	}
	
	public Hospital selectHospitalById(int hospital_id) {
		
		Hospital hospital = null;
		
		try (Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSPITAL_BY_ID );) {
		
		preparedStatement.setInt(1, hospital_id);
			
			System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
			
		rs.first();
		
		int id = rs.getInt("id");
		String nome = rs.getString("nome");
		String telefone = rs.getString("telefone");
		String endereco = rs.getString("endereco");
		String estado = rs.getString("estado");
		int municipio_id = rs.getInt("municipio_id");
		hospital = new Hospital(id, nome, telefone, endereco, estado, municipio_id);
		
		} catch (SQLException e) {
			printSQLException(e);
		}

		return hospital;
	}
	
	public List<Hospital> selectHospitais(int municipio_id) {
		
		List<Hospital> hospitais = new ArrayList<>();
		
		try (Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSPITAIS_BY_MUNICIPIO_ID);) {
		
		preparedStatement.setInt(1, municipio_id);
			
			System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			int leitos_disponiveis = rs.getInt("leitos_disponiveis");
			hospitais.add(new Hospital(id, nome, leitos_disponiveis));
		}
		
		} catch (SQLException e) {
			printSQLException(e);
		}

		return hospitais;
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