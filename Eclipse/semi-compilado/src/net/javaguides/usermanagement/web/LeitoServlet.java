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


@WebServlet(
  urlPatterns = {"/leitos","/leitos/edit","/leitos/update/*"}
  )
public class LeitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeitoDAO leitoDAO;
	private static final String root = "/semi-compilado";
	
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
			case "/leitos/new":
				showNewForm(request, response);
				break;
			case "/leitos/insert":
				insertLeito(request, response);
				break;
			case "/leitos/delete":
				deleteLeito(request, response);
				break;
			case "/leitos/edit":
				showEditForm(request, response);
				break;
			case "/leitos/update":

				System.out.println("\nPedindo GET update");
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
		
		System.out.println("\nPROBLEMA AQUI");
		System.out.println("\nPath: " + request.getContextPath());
		System.out.println("\nQuery: " + request.getQueryString());
		System.out.println("\nURI: " + request.getRequestURI());
		System.out.println("Procurando o JSP do leito (Servlet:listleito)");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-list.jsp");

		
		//response.sendRedirect("..");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Procurando o JSP do leito (Servlet:shownewform)");
		RequestDispatcher dispatcher = request.getRequestDispatcher(root + "/leito-form.jsp");

		//response.sendRedirect("../");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Leito existingLeito = leitoDAO.selectLeito(id);
		
		System.out.println("Procurando o JSP do leito (Servlet:showeditform)");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-form.jsp");
		request.setAttribute("leito", existingLeito);
		
		//response.sendRedirect("..");
		dispatcher.forward(request, response);

	}

	private void insertLeito(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String medico = request.getParameter("medico");
		String enfermeiro = request.getParameter("enfermeiro");
		String paciente = request.getParameter("paciente");
		Leito newLeito = new Leito(medico, enfermeiro, paciente);
		leitoDAO.insertLeito(newLeito);
		response.sendRedirect(".."); //list");
	}

	private void updateLeito(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		System.out.println("\nUPDATE LEITO");
		//System.out.println(request.getParameterValues(""));
		//System.out.println(request.getParameter("salvar"));
		//System.out.println(request.getParameter("liberar"));
		int id = Integer.parseInt(request.getParameter("id"));
		String medico, enfermeiro, paciente;
		
		if(request.getParameter("liberar") == null) {
			System.out.println("Nao liberar");
			medico = request.getParameter("medico");
			enfermeiro = request.getParameter("enfermeiro");
			paciente = request.getParameter("paciente");
		}else {
			System.out.println("Liberar");
			medico = enfermeiro = paciente = "-";
		}
		
		System.out.println("AGora liberou / salvou");

		Leito book = new Leito(id, medico, enfermeiro, paciente);
		leitoDAO.updateLeito(book);
		
		System.out.println("Redirecionando para a lista");
		response.sendRedirect(root + "/leitos");
		//response.forward();
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-list.jsp");
		//response.sendRedirect("..");
		//dispatcher.forward(request, response);
	}

	private void deleteLeito(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		leitoDAO.deleteLeito(id);
		response.sendRedirect("..");//list");

	}

}