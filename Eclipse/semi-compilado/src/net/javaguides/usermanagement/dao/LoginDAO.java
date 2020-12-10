package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Leito;
import net.javaguides.usermanagement.model.Paciente;

/**
 * 
 * @author Hugo Martins
 *
 */

public class LoginDAO {
	private String jdbcURL = "jdbc:mariadb://server3643.ml:3306/db1?useSSL=false";
	private String jdbcUsername = "g1";
	private String jdbcPassword = "1HMUgvW";

	private static final String SELECT_USER_AND_PSSWD = "SELECT cargo FROM funcionarios WHERE login = ? AND senha = ?";

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
	
	public String loginUsuario(String login, String senha) {
		String cargo = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_AND_PSSWD);) {
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, senha);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.first();

			cargo = rs.getString("cargo");
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cargo;
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
