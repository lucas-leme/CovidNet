import java.sql.SQLException;

import net.javaguides.usermanagement.dao.FilaDePacienteDAO;

public class Teste {
	
	public static void main(String [] args) throws SQLException{ 
		
		FilaDePacienteDAO filaDePacienteDAO = new FilaDePacienteDAO();
		
		filaDePacienteDAO.solicitaUti(6);
	}
}
