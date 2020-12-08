package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.javaguides.usermanagement.model.RelatorioVagas;
import net.javaguides.usermanagement.model.Paciente;
import net.javaguides.usermanagement.model.RelatorioUti;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Hugo Martins
 *
 */

public class RelatorioDAO {
	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String RELATORIO_VAGAS_POR_MUNICIPIO = 
			"SELECT hv.data, SUM(hv.vagas_ocupadas) as vagas_ocupadas, SUM(hv.vagas_totais) as vagas_totais FROM historico_vagas hv\n"
			+ "INNER JOIN hospitais h ON hv.hospital_id = h.id \n"
			+ "INNER JOIN municipios m ON h.municipio_id = m.id\n"
			+ "WHERE data >= ? AND data <= ? AND municipio_id = ?\n"
			+ "GROUP BY hv.data";
	
	private static final String RELATORIO_VAGAS_POR_HOSPITAL = 
			"SELECT data, vagas_ocupadas, vagas_totais FROM historico_vagas \n"
			+ "WHERE data >= ? AND data <= ? AND hospital_id = ?";
	
	private static final String RELATORIO_VAGAS_TOTAL = 
			"SELECT hv.data, SUM(hv.vagas_ocupadas) as vagas_ocupadas, SUM(hv.vagas_totais) as vagas_totais FROM historico_vagas hv\n"
			+ "WHERE data >= ? AND data <= ?\n"
			+ "GROUP BY hv.data";
	
	private static final String INSERT_PEDIDO = 
			"INSERT INTO historico_uti (data_pedido, hospital_origem_id)\n"
			+ "VALUES \n"
			+ "(?, ?)";
	
	private static final String INSERT_ALOCACAO = 
			"INSERT INTO historico_uti (data_alocacao, hospital_destino_id)\n"
			+ "VALUES \n"
			+ "(?, ?)";
	
	private static final String RELATORIO_UTI_ALOCACOES_POR_HOSPITAL = 
			"SELECT data_alocacao, COUNT(data_alocacao) as alocacoes FROM historico_uti\n"
			+ "WHERE data_alocacao >= ? AND data_alocacao <= ? AND hospital_destino_id = ?\n"
			+ "GROUP BY data_alocacao ";
	
	private static final String RELATORIO_UTI_PEDIDOS_POR_HOSPITAL = 
			"SELECT data_pedido, COUNT(data_pedido) as pedidos FROM historico_uti\n"
			+ "WHERE data_pedido >= ? AND data_pedido <= ? AND hospital_origem_id = ?\n"
			+ "GROUP BY data_pedido ";
	
	private static final String RELATORIO_UTI_ALOCACOES_POR_MUNICIPIO = 
			"SELECT data_alocacao, COUNT(data_alocacao) as alocacoes FROM historico_uti hu\n"
			+ "INNER JOIN hospitais h ON h.id = hu.hospital_destino_id \n"
			+ "INNER JOIN municipios m ON m.id = h.municipio_id\n"
			+ "WHERE data_alocacao >= ? AND data_alocacao <= ? AND municipio_id = ?\n"
			+ "GROUP BY data_alocacao ";

	private static final String RELATORIO_UTI_PEDIDOS_POR_MUNICIPIO = 
			"SELECT data_pedido, COUNT(data_pedido) as pedidos FROM historico_uti hu\n"
			+ "INNER JOIN hospitais h ON h.id = hu.hospital_origem_id \n"
			+ "INNER JOIN municipios m ON m.id = h.municipio_id\n"
			+ "WHERE data_pedido >= ? AND data_pedido <= ? AND municipio_id = ?\n"
			+ "GROUP BY data_pedido ";
	
	private static final String RELATORIO_UTI_ALOCACOES_TOTAIS = 
			"SELECT data_alocacao , COUNT(data_alocacao) as alocacoes FROM historico_uti hu\n"
			+ "WHERE data_alocacao >= ? AND data_alocacao <= ?\n"
			+ "GROUP BY data_alocacao ";
	
	private static final String RELATORIO_UTI_PEDIDOS_TOTAIS = 
			"SELECT data_pedido, COUNT(data_pedido) as pedidos FROM historico_uti hu\n"
			+ "WHERE data_pedido >=  AND data_pedido <= ?\n"
			+ "GROUP BY data_pedido ";

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
	
	public void insertPedido(int hospital_origem_id) throws SQLException {
		
		try (Connection connection = getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PEDIDO)) {
			preparedStatement.setString(1, (new Date()).toString());
			preparedStatement.setInt(2, hospital_origem_id);
	
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void insertAlocacao(int hospital_destino_id) throws SQLException {
		
		try (Connection connection = getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ALOCACAO)) {
			preparedStatement.setString(1, (new Date()).toString());
			preparedStatement.setInt(2, hospital_destino_id);
	
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public List<RelatorioVagas> RelatorioVagasHospital(String data_inicio, String data_fim, int hospital_id) {
		
		List<RelatorioVagas> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_VAGAS_POR_HOSPITAL);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			preparedStatement.setInt(3, hospital_id);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data = rs.getString("data");
				int vagas_ocupadas = rs.getInt("vagas_ocupadas");
				int vagas_totais = rs.getInt("vagas_totais");
				
				relatorio.add(new RelatorioVagas(data, vagas_ocupadas, vagas_totais));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}
	
	public List<RelatorioVagas> RelatorioVagasMunicipio(String data_inicio, String data_fim, int municipio_id) {
		
		List<RelatorioVagas> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_VAGAS_POR_MUNICIPIO);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			preparedStatement.setInt(3, municipio_id);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data = rs.getString("data");
				int vagas_ocupadas = rs.getInt("vagas_ocupadas");
				int vagas_totais = rs.getInt("vagas_totais");
				
				relatorio.add(new RelatorioVagas(data, vagas_ocupadas, vagas_totais));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}
	
	public List<RelatorioVagas> RelatorioVagasTotal(String data_inicio, String data_fim) {
		
		List<RelatorioVagas> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_VAGAS_TOTAL);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data = rs.getString("data");
				int vagas_ocupadas = rs.getInt("vagas_ocupadas");
				int vagas_totais = rs.getInt("vagas_totais");
				
				relatorio.add(new RelatorioVagas(data, vagas_ocupadas, vagas_totais));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}
	
	public List<RelatorioUti> RelatorioUtiPedidosPorHospital(String data_inicio, String data_fim, int hospital_origem_id) {
		
		List<RelatorioUti> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_UTI_PEDIDOS_POR_HOSPITAL);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			preparedStatement.setInt(3, hospital_origem_id);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data_pedido = rs.getString("data_pedido");
				int pedidos = rs.getInt("pedidos");
				
				relatorio.add(new RelatorioUti(data_pedido, pedidos));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}
	
	public List<RelatorioUti> RelatorioUtiAlocacoesPorHospital(String data_inicio, String data_fim, int hospital_destino_id) {
		
		List<RelatorioUti> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_UTI_ALOCACOES_POR_HOSPITAL);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			preparedStatement.setInt(3, hospital_destino_id);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data_alocacao = rs.getString("data_alocacao");
				int alocacoes = rs.getInt("alocacoes");
				
				relatorio.add(new RelatorioUti(alocacoes, data_alocacao));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}
	
	public List<RelatorioUti> RelatorioUtiPedidosPorMunicipio(String data_inicio, String data_fim, int municipio_id) {
		
		List<RelatorioUti> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_UTI_PEDIDOS_POR_MUNICIPIO);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			preparedStatement.setInt(3, municipio_id);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data_pedido = rs.getString("data_pedido");
				int pedidos = rs.getInt("pedidos");
				
				relatorio.add(new RelatorioUti(data_pedido, pedidos));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}
	
	public List<RelatorioUti> RelatorioUtiAlocacoesPorMunicipio(String data_inicio, String data_fim, int municipio_id) {
		
		List<RelatorioUti> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_UTI_ALOCACOES_POR_MUNICIPIO);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			preparedStatement.setInt(3, municipio_id);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data_alocacao = rs.getString("data_alocacao");
				int alocacoes = rs.getInt("alocacoes");
				
				relatorio.add(new RelatorioUti(alocacoes, data_alocacao));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}
	
	public List<RelatorioUti> RelatorioUtiPedidosTotais(String data_inicio, String data_fim) {
		
		List<RelatorioUti> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_UTI_PEDIDOS_TOTAIS);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data_pedido = rs.getString("data_pedido");
				int pedidos = rs.getInt("pedidos");
				
				relatorio.add(new RelatorioUti(data_pedido, pedidos));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
	}
	
	public List<RelatorioUti> RelatorioUtiAlocacoesTotais(String data_inicio, String data_fim) {
		
		List<RelatorioUti> relatorio = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RELATORIO_UTI_ALOCACOES_TOTAIS);) {
			
			preparedStatement.setString(1, data_inicio);
			preparedStatement.setString(2, data_fim);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String data_alocacao = rs.getString("data_alocacao");
				int alocacoes = rs.getInt("alocacoes");
				
				relatorio.add(new RelatorioUti(alocacoes, data_alocacao));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return relatorio;
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
