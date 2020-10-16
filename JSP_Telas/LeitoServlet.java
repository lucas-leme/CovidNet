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

import net.javaguides.usermanagement.dao.LeitoDAO;
import net.javaguides.usermanagement.model.Leito;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the leito.
 * @enfermeiro Ramesh Fadatare
 */

@WebServlet("/")
public class LeitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeitoDAO leitoDAO;
	
	public void init() {
		leitoDAO = new LeitoDAO();
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
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertLeito(request, response);
				break;
			case "/delete":
				deleteLeito(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateLeito(request, response);
				break;
			default:
				listLeito(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listLeito(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Leito> listLeito = leitoDAO.selectAllLeitos();
		request.setAttribute("listLeito", listLeito);
		RequestDispatcher dispatcher = request.getRequestDispatcher("leito-user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("leito-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Leito existingLeito = leitoDAO.selectLeito(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("leito-form.jsp");
		request.setAttribute("leito", existingLeito);
		dispatcher.forward(request, response);

	}

	private void insertLeito(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String medico = request.getParameter("medico");
		String enfermeiro = request.getParameter("enfermeiro");
		String paciente = request.getParameter("paciente");
		Leito newLeito = new Leito(medico, enfermeiro, paciente);
		leitoDAO.insertLeito(newLeito);
		response.sendRedirect("list");
	}

	private void updateLeito(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String medico = request.getParameter("medico");
		String enfermeiro = request.getParameter("enfermeiro");
		String paciente = request.getParameter("paciente");

		Leito book = new Leito(id, medico, enfermeiro, paciente);
		leitoDAO.updateLeito(book);
		response.sendRedirect("list");
	}

	private void deleteLeito(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		leitoDAO.deleteLeito(id);
		response.sendRedirect("list");

	}

}
