package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Hospital;
import net.javaguides.usermanagement.model.Municipio;

public class HospitalDAO {
	
	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";
	
	private static final String SELECT_ALL_MUNICIPIOS = "SELECT * FROM municipios";
	
	private static final String SELECT_HOPITAIS_BY_MUNICIPIO_ID = 
			"SELECT h.nome, COUNT(*) as 'leitos_disponiveis'"
			+ " FROM"
			+ "    hospitais h"
			+ "    JOIN leitos l"
			+ "    ON h.id = l.hospital_id"
			+ " WHERE h.municipio_id = ? AND l.ocupado = 0"
			+ " GROUP BY h.nome";	
	
	private static final String SELECT_ALL_HOPITAIS = 
			"SELECT h.id, h.nome, h.qtde_leitos"
			+ " FROM"
			+ "    hospitais h";
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

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_HOPITAIS);){//, Statement.RETURN_GENERATED_KEYS);) {
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
	            //ResultSet rs2 = preparedStatement.getGeneratedKeys();
	            
	            int id_hospital = 123;
	            //if(rs2.next()) id_hospital = rs2.getInt(1);
	            
	            id_hospital = rs.getInt("id");
	            
				
				String nome = rs.getString("nome");
				int qtde_leitos = rs.getInt("qtde_leitos");
				//System.out.println("Leitos disponiveis de " + nome + " : " + qtde_leitos);

	            //System.out.println("id do hospital " + nome + " : " + id_hospital);
				
				hospitais.add(new Hospital(id_hospital, nome, qtde_leitos));
			}
		
		} catch (SQLException e) {
			printSQLException(e);
		}

		return hospitais;		
	}
	
	public List<Hospital> selectHospitais(int municipio_id) {
		
		List<Hospital> hospitais = new ArrayList<>();
		
		try (Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOPITAIS_BY_MUNICIPIO_ID);) {
			
			preparedStatement.setString(1, Integer.toString(municipio_id));
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				
				String nome = rs.getString("nome");
				int leitos_disponiveis = rs.getInt("leitos_disponiveis");
				hospitais.add(null);//new Hospital(nome, leitos_disponiveis));
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

	public List<String> selectCidades() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getID(String cidade) {
		// TODO Auto-generated method stub
		return 0;
	}
}
