package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagement.dao.FilaDePacienteDAO;
import net.javaguides.usermanagement.model.FilaDePaciente;
import net.javaguides.usermanagement.model.Paciente;

@WebServlet(
  urlPatterns = {"/fila_de_pacientes","/fila_de_pacientes/solicita_uti", "/fila_de_pacientes/list","/fila_de_pacientes/pega_primeiro"}
  )
public class FilaDePacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FilaDePacienteDAO filaDePacienteDAO;
	private static final String root = "/semi-compilado";
	
	public void init() {
		filaDePacienteDAO = new FilaDePacienteDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/fila_de_pacientes/solicita_uti":
				solicitaUti(request, response);
				break;
			case "/fila_de_pacientes/list":
				mostraFila(request, response);
				break;
			case "/fila_de_pacientes/pega_primeiro":
				pegaPrimeiro(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void solicitaUti(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		int prontuario_id = Integer.parseInt(request.getParameter("prontuario_id"));

		filaDePacienteDAO.solicitaUti(prontuario_id);
		
		response.sendRedirect(root + "/fila_de_pacientes");
	}
	
	private void mostraFila(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<FilaDePaciente> lista_de_pacientes = filaDePacienteDAO.selectAllPacientesNaFila();
		
		request.setAttribute("mostraFila", lista_de_pacientes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/fila_de_pacientes-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void pegaPrimeiro(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Paciente paciente = filaDePacienteDAO.selectPrimeiroPacienteDaFila();
		
		request.setAttribute("pegaPrimeiro", paciente);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/fila_de_pacientes.jsp");
		dispatcher.forward(request, response);
	}
	
}