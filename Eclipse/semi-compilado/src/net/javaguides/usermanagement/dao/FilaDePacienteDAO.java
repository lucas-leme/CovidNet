package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.FilaDePaciente;
import net.javaguides.usermanagement.model.Paciente;
import net.javaguides.usermanagement.model.Prontuario;

public class FilaDePacienteDAO {
	
	private ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
	private PacienteDAO pacienteDAO = new PacienteDAO();

	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";
	
	private static final String SELECT_ALL_PACIENTES_NA_FILA = 
			"SELECT f.id, f.ordem, f.data, f.prioridade, p.nome "
			+ " FROM fila_de_pacientes f"
			+ " INNER JOIN pacientes p"
			+ " ON f.paciente_id = p.id"
			+ " ORDER BY ordem ASC";
	
	private static final String SELECT_PACIENTES_POR_PRIORIDADE =
			"SELECT id FROM fila_de_pacientes WHERE prioridade > ? ORDER BY ordem ASC";
	
	private static final String SELECT_PRIMEIRO_DA_FILA =
			"SELECT * FROM fila_de_pacientes WHERE ordem = 1";

	private static final String INSERT_PACIENTE_NA_FILA =
			"INSERT INTO fila_de_pacientes (ordem, prioridade, paciente_id)" 
			+ " VALUES (?, ?, ?)";

	
	private static final String UPDATE_ORDEM_DE_PACIENTE_NA_FILA = "UPDATE fila_de_pacientes SET ordem = ? WHERE id = ?";		
	
	private static final String COUNT_PACIENTES_NA_FILA = 
			"SELECT COUNT(*) as 'quantidade_pacientes_antes'"
			+ "	FROM fila_de_pacientes"
			+ "	WHERE prioridade <= ?";
	
	private static final String REMOVE_PACIENTES_DA_FILA = 
			"DELETE FROM fila_de_pacientes WHERE paciente_id = ?";
	
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
	
	public void solicitaUti(int prontuario_id) throws SQLException {
				
		int prioridade;
		int quantidade_pacientes_antes = 0;
		List<FilaDePaciente> fila_de_pacientes = new ArrayList<>();
				
		Prontuario prontuario = prontuarioDAO.selectProntuarioById(prontuario_id);

		if (prontuario.getVentilacaoMecanica()) {
			prioridade = 1;
		} else if (prontuario.getOximetria() < 94) {
			prioridade = 2;
		} else if (!prontuario.getTomografiaToraxNormal()) {
			prioridade = 3;
		} else if (prontuario.getTesteCovid().equals("Positivo - swab") && prontuario.getDoencaRespiratoria()) {
			prioridade = 4;
		} else if (prontuario.getTesteCovid().equals("Positivo - swab") && !prontuario.getRadiometriaToraxNormal()) {
			prioridade = 5;
		} else if (!prontuario.getBatimentoCardiacoNormal() && (prontuario.getDiabetes() || prontuario.getObesidade() || prontuario.getHipertensao())) {
			prioridade = 6;
		} else {
			prioridade = 7;
		}
				
		// seleciona pacientes com prioridade menor que a do paciente atual
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PACIENTES_POR_PRIORIDADE);) {
			preparedStatement.setInt(1, prioridade);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				fila_de_pacientes.add(new FilaDePaciente(id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
				
		// conta quantidade de pacientes com prioridade maior que a do pacinte atual
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PACIENTES_NA_FILA);) {
				preparedStatement.setInt(1, prioridade);
				ResultSet rs = preparedStatement.executeQuery();
			
				rs.first();
				
				quantidade_pacientes_antes = rs.getInt("quantidade_pacientes_antes");

		} catch (SQLException e) {
			printSQLException(e);
		}

		// insere paciente atual na fila
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTE_NA_FILA)) {
			preparedStatement.setInt(1, quantidade_pacientes_antes + 1);
			preparedStatement.setInt(2, prioridade);
			preparedStatement.setInt(3, prontuario.getPacienteId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}

		// atualiza ordem dos pacientes com prioridade menor que a do paciente atual
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_ORDEM_DE_PACIENTE_NA_FILA);) {
				
			for (int i = 0; i < fila_de_pacientes.size(); i++) {
				statement.setInt(1, quantidade_pacientes_antes + i + 2);
				statement.setInt(2, fila_de_pacientes.get(i).getId());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public List<FilaDePaciente> selectAllPacientesNaFila() {
		List<FilaDePaciente> fila_de_pacientes = new ArrayList<>();;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PACIENTES_NA_FILA);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String data = rs.getString("data");
				int ordem = rs.getInt("ordem");
				int prioridade = rs.getInt("prioridade");
				String nome = rs.getString("nome");
									
				fila_de_pacientes.add(new FilaDePaciente(id, data, ordem, prioridade, nome));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return fila_de_pacientes;
	}
	
	public Paciente selectPrimeiroPacienteDaFila() {
		
		FilaDePaciente primeiro_da_fila = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRIMEIRO_DA_FILA);) {
			System.out.println("Selecting first paciente");
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			rs.first();
			
			int id = rs.getInt("id");
			String data = rs.getString("data");
			int ordem = rs.getInt("ordem");
			int prioridade = rs.getInt("prioridade");
			int paciente_id = rs.getInt("paciente_id");
					
			primeiro_da_fila = new FilaDePaciente(id, data, ordem, prioridade, paciente_id);
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return pacienteDAO.selectPacienteById(primeiro_da_fila.getPacienteId());
	}
	
	public void alocaPrimeiroPacienteDaFila(int primeiro_da_fila_id, int hospital_de_destino_id) throws SQLException {
		
		// remove da fila o primeiro paciente
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(REMOVE_PACIENTES_DA_FILA);) {
			statement.setInt(1, primeiro_da_fila_id);
			System.out.println(statement);
			statement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		// atualiza hospital de destino
		prontuarioDAO.updateHospitalDeDestino(hospital_de_destino_id, primeiro_da_fila_id);
		
		List<FilaDePaciente> fila_de_pacientes = this.selectAllPacientesNaFila();
		
		// atualiza ordem dos pacientes
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_ORDEM_DE_PACIENTE_NA_FILA);) {
				
			for (int i = 0; i < fila_de_pacientes.size(); i++) {
				statement.setInt(1, fila_de_pacientes.get(i).getOrdem() - 1);
				statement.setInt(2, fila_de_pacientes.get(i).getId());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			printSQLException(e);
		}	
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
