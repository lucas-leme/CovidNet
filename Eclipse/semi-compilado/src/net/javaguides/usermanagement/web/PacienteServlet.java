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

import net.javaguides.usermanagement.dao.PacienteDAO;
import net.javaguides.usermanagement.model.Paciente;

/**
 * pacienteServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Hugo Martins
 */

@WebServlet("/pacientes")
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PacienteDAO pacienteDAO;
	
	public void init() {
		pacienteDAO = new PacienteDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			
			System.out.println("acao\n" + action);
			
			switch (action) {
			case "/pacientes/new":
				showNewForm(request, response);
				break;
			case "/pacientes/insert":
				insertPaciente(request, response);
				break;
			case "/pacientes/delete":
				deletePaciente(request, response);
				break;
			case "/pacientes/edit":
				showEditForm(request, response);
				break;
			case "/pacientes/update":
				updatePaciente(request, response);
				break;
			default:
				listPacientes(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listPacientes(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Paciente> listPacientes = pacienteDAO.selectAllPacientes();
		request.setAttribute("listPacientes", listPacientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("paciente-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("paciente-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Paciente existingPaciente = pacienteDAO.selectPaciente(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("paciente-form.jsp");
		request.setAttribute("paciente", existingPaciente);
		dispatcher.forward(request, response);
	}

	private void insertPaciente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String prontuario = request.getParameter("prontuario");
		String dataDeEntrada = request.getParameter("dataDeEntrada");
		Paciente newPaciente = new Paciente(cpf, nome, prontuario , dataDeEntrada);
		pacienteDAO.insertPaciente(newPaciente);
		response.sendRedirect("pacientes");
	}

	private void updatePaciente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String prontuario = request.getParameter("prontuario");
		String dataDeEntrada = request.getParameter("dataDeEntrada");  

		Paciente book = new Paciente(id, cpf, nome, prontuario , dataDeEntrada);
		pacienteDAO.updatePaciente(book);
		response.sendRedirect("pacientes");
	}

	private void deletePaciente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println("id\n" + id);
		
		pacienteDAO.deletePaciente(id);
		response.sendRedirect("pacientes");
	}
}
