package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.javaguides.usermanagement.model.Paciente;

public class PacienteDAO {

	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String INSERT_PACIENTE_SQL = "INSERT INTO pacientes (cpf, nome, data_de_nascimento, endereco) VALUES  (?, ?, ?, ?)";
	
	private static final String SELECT_PACIENTE_BY_CPF = "SELECT id, cpf, nome, data_de_nascimento, endereco FROM pacientes WHERE cpf = ?";
	
	private static final String SELECT_PACIENTE_BY_ID = "SELECT id, cpf, nome, data_de_nascimento, endereco FROM pacientes WHERE id = ?";
	
	private static final String UPDATE_PACIENTE = "UPDATE pacientes SET cpf = ?, nome = ?, data_de_nascimento = ?, endereco = ? WHERE id = ?";	
	
	private static final String SELECT_HOSPITAL_DE_DESTINO_PACIENTE = "SELECT pr.hospital_destino_id FROM prontuarios pr \n"
			+ "INNER JOIN pacientes pa ON pa.id = pr.paciente_id WHERE pa.id = ?";

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
	
	public int insertPaciente(Paciente paciente) throws SQLException {
		
		try (Connection connection = getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, paciente.getCpf());
			preparedStatement.setString(2, paciente.getNome());
			preparedStatement.setString(3, paciente.getDataDeNascimento());
			preparedStatement.setString(4, paciente.getEndereco());
			
			preparedStatement.executeUpdate();
			
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
                return rs.getInt(1);
            }
            
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		System.out.println("ID nao encontrado: erro de sql");
		return -1; // MUDAR PARA ID DO PACIENTE QUANDO E CRIADO
	}
	
	public void updatePaciente(Paciente paciente) throws SQLException {
		
		try (Connection connection = getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PACIENTE, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, paciente.getCpf());
			preparedStatement.setString(2, paciente.getNome());
			preparedStatement.setString(3, paciente.getDataDeNascimento());
			preparedStatement.setString(4, paciente.getEndereco());
			preparedStatement.setInt(5, paciente.getId());
			
			System.out.println(preparedStatement);
			
			preparedStatement.executeUpdate();
            
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Paciente selectPacienteByCpf(String cpf) {
		Paciente paciente = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PACIENTE_BY_CPF);) {
			preparedStatement.setString(1, cpf);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.first();

			int id = rs.getInt("id");
			String cpf2 = rs.getString("cpf");
			String nome = rs.getString("nome");
			String data_de_nascimento = rs.getString("data_de_nascimento");
			String endereco = rs.getString("endereco");
					
			paciente = new Paciente(id, cpf2, nome, data_de_nascimento, endereco);
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return paciente;
	}
	
	public Paciente selectPacienteById(int id) {
		Paciente paciente = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PACIENTE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.first();

			String cpf = rs.getString("cpf");
			String nome = rs.getString("nome");
			String data_de_nascimento = rs.getString("data_de_nascimento");
			String endereco = rs.getString("endereco");
						
			paciente = new Paciente(id, cpf, nome, data_de_nascimento, endereco);
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return paciente;
	}
	
	public int selectHospitalDeDestinoId(int paciente_id) {
		
		int hospital_destino_id = 0;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSPITAL_DE_DESTINO_PACIENTE);) {
			preparedStatement.setInt(1, paciente_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.first();

			hospital_destino_id = rs.getInt("hospital_destino_id");
					
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return hospital_destino_id;
	}

	public boolean updateProntuario(Paciente paciente) throws SQLException {
		boolean rowUpdated;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PACIENTE);) {
			
			statement.setString(1, paciente.getCpf());
			statement.setString(2, paciente.getNome());
			statement.setString(3, paciente.getDataDeNascimento());
			statement.setString(4, paciente.getEndereco());
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
