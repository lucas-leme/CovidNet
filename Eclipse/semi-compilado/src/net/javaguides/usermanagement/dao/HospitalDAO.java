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
	
	private static final String SELECT_HOSPITAIS_BY_MUNICIPIO_ID = 
			"SELECT h.id, h.nome, COUNT(*) as 'leitos_disponiveis'"
			+ "FROM"
			+ "    hospitais h"
			+ "    JOIN leitos l"
			+ "    ON h.id = l.hospital_id"
			+ "WHERE h.municipio_id = ? AND l.ocupado = 0"
			+ "GROUP BY h.nome";
	
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
	
	public List<Hospital> selectHospitais(int municipio_id) {
		
		List<Hospital> hospitais = new ArrayList<>();
		
		try (Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSPITAIS_BY_MUNICIPIO_ID);) {
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