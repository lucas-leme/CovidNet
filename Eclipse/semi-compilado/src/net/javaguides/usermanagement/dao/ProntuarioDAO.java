package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Prontuario;

public class ProntuarioDAO {

	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String INSERT_PRONTUARIO =	 
			"INSERT INTO prontuarios ("
			+ "		estado_do_paciente,"
			+ "		diagnostico,"
			+ "		teste_covid,"
			+ "		doenca_respiratoria,"
			+ "		batimento_cardiaco_normal,"
			+ "		hipertensao,"
			+ "		oximetria,"
			+ "		radiometria_torax_normal,"
			+ "		tomografia_torax_normal,"
			+ "		ventilacao_mecanica,"
			+ "		diabetes,"
			+ "		obesidade,"
			+ "		ativo,"
			+ "		hospital_id,"
			+ "		paciente_id"
			+ ") "
			+ "VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";	
		
	private static final String SELECT_PRONTUARIO_BY_PACIENTE_CPF =
			"SELECT"
			+ "		pro.id,"
			+ "		pro.data,"
			+ "		pro.estado_do_paciente,"
			+ "		pro.diagnostico,"
			+ "		pro.teste_covid,"
			+ "		pro.doenca_respiratoria,"
			+ "		pro.batimento_cardiaco_normal,"
			+ "		pro.hipertensao,"
			+ "		pro.oximetria,"
			+ "		pro.radiometria_torax_normal,"
			+ "		pro.tomografia_torax_normal,"
			+ "		pro.ventilacao_mecanica,"
			+ "		pro.diabetes,"
			+ "		pro.obesidade,"
			+ "		pro.ativo,"
			+ "		pro.hospital_id,"
			+ "		pro.hospital_destino_id,"
			+ "		pro.paciente_id"
			+ "	FROM prontuarios pro"
			+ "	INNER JOIN pacientes pac ON pac.id = pro.paciente_id"
			+ "	WHERE pac.cpf = ? AND pro.ativo = 1";
	
	private static final String SELECT_PRONTUARIOS_BY_HOSPITAL =
			"SELECT"
			+ "		id,"
			+ "		data,"
			+ "		estado_do_paciente,"
			+ "		diagnostico,"
			+ "		teste_covid,"
			+ "		doenca_respiratoria,"
			+ "		batimento_cardiaco_normal,"
			+ "		hipertensao,"
			+ "		oximetria,"
			+ "		radiometria_torax_normal,"
			+ "		tomografia_torax_normal,"
			+ "		ventilacao_mecanica,"
			+ "		diabetes,"
			+ "		obesidade,"
			+ "		ativo,"
			+ "		hospital_id,"
			+ "		hospital_destino_id,"
			+ "		paciente_id"
			+ " FROM prontuarios"
			+ " WHERE hospital_id = ? AND ativo = 1";
	
	private static final String SELECT_PRONTUARIO_BY_ID =
			"SELECT"
			+ "		data,"
			+ "		estado_do_paciente,"
			+ "		diagnostico,"
			+ "		teste_covid,"
			+ "		doenca_respiratoria,"
			+ "		batimento_cardiaco_normal,"
			+ "		hipertensao,"
			+ "		oximetria,"
			+ "		radiometria_torax_normal,"
			+ "		tomografia_torax_normal,"
			+ "		ventilacao_mecanica,"
			+ "		diabetes,"
			+ "		obesidade,"
			+ "		ativo,"
			+ "		hospital_id,"
			+ "		hospital_destino_id,"
			+ "		paciente_id"
			+ " FROM prontuarios"
			+ " WHERE id = ?";
	
	private static final String UPDATE_PRONTUARIO =
			"UPDATE prontuarios "
			+ "SET"
			+ "		estado_do_paciente = ?,"
			+ "		diagnostico = ?,"
			+ "		teste_covid = ?,"
			+ "		doenca_respiratoria = ?,"
			+ "		batimento_cardiaco_normal = ?,"
			+ "		hipertensao = ?,"
			+ "		oximetria = ?,"
			+ "		radiometria_torax_normal = ?,"
			+ "		tomografia_torax_normal = ?,"
			+ "		ventilacao_mecanica = ?,"
			+ "		diabetes = ?,"
			+ "		obesidade = ?,"
			+ "		ativo = ?,"
			+ "		hospital_id = ?"
			+ "	WHERE id = ? AND ativo = 1";
	
	private static final String UPDATE_HOSPITAL_DE_DESTINO =
			"UPDATE prontuarios "
			+ "SET hospital_destino_id = ?"
			+ "	WHERE paciente_id = ?";
	
	private static final String CLOSE_PRONTUARIO =
			"UPDATE prontuarios "
			+ "	SET	ativo = 0"
			+ "	WHERE id = ?";
	
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
	
	public void insertProntuario(Prontuario prontuario, int id_hospital, int id_paciente) throws SQLException {

		System.out.println("PRONTUARIO DAO: inserting prontuario");
		System.out.println(INSERT_PRONTUARIO);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRONTUARIO)) {
			preparedStatement.setString(1, prontuario.getEstadoDoPaciente());
			preparedStatement.setString(2, prontuario.getDiagnostico());
			preparedStatement.setString(3, prontuario.getTesteCovid());
			preparedStatement.setBoolean(4, prontuario.getDoencaRespiratoria());
			preparedStatement.setBoolean(5, prontuario.getBatimentoCardiacoNormal());
			preparedStatement.setBoolean(6, prontuario.getHipertensao());
			preparedStatement.setInt(7, prontuario.getOximetria());
			preparedStatement.setBoolean(8, prontuario.getRadiometriaToraxNormal());
			preparedStatement.setBoolean(9, prontuario.getTomografiaToraxNormal());
			preparedStatement.setBoolean(10, prontuario.getVentilacaoMecanica());
			preparedStatement.setBoolean(11, prontuario.getDiabetes());
			preparedStatement.setBoolean(12, prontuario.getObesidade());
			preparedStatement.setBoolean(13, prontuario.getAtivo());
			
			preparedStatement.setInt(14, prontuario.getHospitalId()); //hospital de origem
			preparedStatement.setInt(15, prontuario.getPacienteId());
			
			System.out.println("Prontuario pra ser inserido = " + preparedStatement);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Prontuario selectProntuarioByPacienteCpf(String cpf) {
		Prontuario prontuario = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRONTUARIO_BY_PACIENTE_CPF);) {
			preparedStatement.setString(1, cpf);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String data = rs.getString("data");
				String estado_do_paciente = rs.getString("estado_do_paciente");
				String diagnostico = rs.getString("diagnostico");
				String teste_covid = rs.getString("teste_covid");
				boolean doenca_respiratoria = rs.getBoolean("doenca_respiratoria");
				boolean batimento_cardiaco_normal = rs.getBoolean("batimento_cardiaco_normal");
				boolean hipertensao = rs.getBoolean("hipertensao");
				int oximetria = rs.getInt("oximetria");
				boolean radiometria_torax_normal = rs.getBoolean("radiometria_torax_normal");
				boolean tomografia_torax_normal = rs.getBoolean("tomografia_torax_normal");
				boolean ventilacao_mecanica = rs.getBoolean("ventilacao_mecanica");
				boolean diabetes = rs.getBoolean("diabetes");
				boolean obesidade = rs.getBoolean("obesidade");
				boolean ativo = rs.getBoolean("ativo");
				int hospital_id = rs.getInt("hospital_id");
				int hospital_destino_id = rs.getInt("hospital_destino_id");
				int paciente_id = rs.getInt("paciente_id");
				
				System.out.println("HOSPITAL DESTINO " + hospital_destino_id);
				
				System.out.println("PARAMTROS PRONTUARIO:");
				System.out.println(id + data + estado_do_paciente + diagnostico + teste_covid + doenca_respiratoria + batimento_cardiaco_normal + 
						hipertensao + oximetria + radiometria_torax_normal + tomografia_torax_normal + ventilacao_mecanica + diabetes + obesidade);
				
				
				prontuario = new Prontuario(
					id,
					data,
					estado_do_paciente,
					diagnostico,
					teste_covid,
					doenca_respiratoria,
					batimento_cardiaco_normal,
					hipertensao,
					oximetria,
					radiometria_torax_normal,
					tomografia_torax_normal,
					ventilacao_mecanica,
					diabetes,
					obesidade,
					ativo,
					hospital_id,
					hospital_destino_id,
					paciente_id
				);

			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prontuario;
	}

	public Prontuario selectProntuarioById(int id) {
		Prontuario prontuario = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRONTUARIO_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data = rs.getString("data");
				String estado_do_paciente = rs.getString("estado_do_paciente");
				String diagnostico = rs.getString("diagnostico");
				String teste_covid = rs.getString("teste_covid");
				boolean doenca_respiratoria = rs.getBoolean("doenca_respiratoria");
				boolean batimento_cardiaco_normal = rs.getBoolean("batimento_cardiaco_normal");
				boolean hipertensao = rs.getBoolean("hipertensao");
				int oximetria = rs.getInt("oximetria");
				boolean radiometria_torax_normal = rs.getBoolean("radiometria_torax_normal");
				boolean tomografia_torax_normal = rs.getBoolean("tomografia_torax_normal");
				boolean ventilacao_mecanica = rs.getBoolean("ventilacao_mecanica");
				boolean diabetes = rs.getBoolean("diabetes");
				boolean obesidade = rs.getBoolean("obesidade");
				boolean ativo = rs.getBoolean("ativo");
				int hospital_id = rs.getInt("hospital_id");
				int hospital_destino_id = rs.getInt("hospital_destino_id");
				int paciente_id = rs.getInt("paciente_id");
				
				prontuario = new Prontuario(
					id,
					data,
					estado_do_paciente,
					diagnostico,
					teste_covid,
					doenca_respiratoria,
					batimento_cardiaco_normal,
					hipertensao,
					oximetria,
					radiometria_torax_normal,
					tomografia_torax_normal,
					ventilacao_mecanica,
					diabetes,
					obesidade,
					ativo,
					hospital_id,
					hospital_destino_id,
					paciente_id
				);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prontuario;
	}
	
	public List<Prontuario> selectProntuariosByHospital() {

		List<Prontuario> prontuarios = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRONTUARIOS_BY_HOSPITAL);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String data = rs.getString("data");
				String estado_do_paciente = rs.getString("estado_do_paciente");
				String diagnostico = rs.getString("diagnostico");
				String teste_covid = rs.getString("teste_covid");
				boolean doenca_respiratoria = rs.getBoolean("doenca_respiratoria");
				boolean batimento_cardiaco_normal = rs.getBoolean("batimento_cardiaco_normal");
				boolean hipertensao = rs.getBoolean("hipertensao");
				int oximetria = rs.getInt("oximetria");
				boolean radiometria_torax_normal = rs.getBoolean("radiometria_torax_normal");
				boolean tomografia_torax_normal = rs.getBoolean("tomografia_torax_normal");
				boolean ventilacao_mecanica = rs.getBoolean("ventilacao_mecanica");
				boolean diabetes = rs.getBoolean("diabetes");
				boolean obesidade = rs.getBoolean("obesidade");
				boolean ativo = rs.getBoolean("ativo");
				int hospital_id = rs.getInt("hospital_id");
				int hospital_destino_id = rs.getInt("hospital_destino_id");
				int paciente_id = rs.getInt("paciente_id");
				
				prontuarios.add(new Prontuario(
					id,
					data,
					estado_do_paciente,
					diagnostico,
					teste_covid,
					doenca_respiratoria,
					batimento_cardiaco_normal,
					hipertensao,
					oximetria,
					radiometria_torax_normal,
					tomografia_torax_normal,
					ventilacao_mecanica,
					diabetes,
					obesidade,
					ativo,
					hospital_id,
					hospital_destino_id,
					paciente_id
				));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prontuarios;
	}

	public boolean updateProntuario(Prontuario prontuario) throws SQLException {
		boolean rowUpdated;
			
		Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRONTUARIO);
			
				
			statement.setString(1, prontuario.getEstadoDoPaciente());
			statement.setString(2, prontuario.getDiagnostico());
			statement.setString(3, prontuario.getTesteCovid());
			statement.setBoolean(4, prontuario.getDoencaRespiratoria());
			statement.setBoolean(5, prontuario.getBatimentoCardiacoNormal());
			statement.setBoolean(6, prontuario.getHipertensao());
			statement.setInt(7, prontuario.getOximetria());
			statement.setBoolean(8, prontuario.getRadiometriaToraxNormal());
			statement.setBoolean(9, prontuario.getTomografiaToraxNormal());
			statement.setBoolean(10, prontuario.getVentilacaoMecanica());
			statement.setBoolean(11, prontuario.getDiabetes());
			statement.setBoolean(12, prontuario.getObesidade());
			statement.setBoolean(13, prontuario.getAtivo());
			statement.setInt(14, prontuario.getHospitalId());
			//statement.setInt(15, prontuario.getHospitalDestinoId());
			statement.setInt(15, prontuario.getId());

			System.out.println(statement);

			System.out.println(statement);
			
			rowUpdated = statement.executeUpdate() > 0;

			System.out.println("data: "+ prontuario.getData());
			System.out.println("obesidae: " + prontuario.getObesidade());
			System.out.println("execute update: " + statement.executeUpdate());
			System.out.println("id: " + prontuario.getId());
			System.out.println("rowUPdated??" + rowUpdated);

		return rowUpdated;
	}
	
	public boolean updateHospitalDeDestino(int hospital_de_destino_id, int paciente_id) throws SQLException {
		boolean rowUpdated;
			
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_HOSPITAL_DE_DESTINO);) {
			
			statement.setInt(1, hospital_de_destino_id);
			statement.setInt(2, paciente_id);
		
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean closeProntuario(int id) throws SQLException {
		boolean rowUpdated;
			
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CLOSE_PRONTUARIO);) {
			
			statement.setInt(1, id);
			
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
