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
import net.javaguides.usermanagement.dao.HospitalDAO;
import net.javaguides.usermanagement.dao.LeitoDAO;
import net.javaguides.usermanagement.dao.PacienteDAO;
import net.javaguides.usermanagement.model.Hospital;
import net.javaguides.usermanagement.model.Leito;
import net.javaguides.usermanagement.model.Municipio;
import net.javaguides.usermanagement.model.Paciente;


@WebServlet(
  urlPatterns = {"/login", "/login/signin", "/login/signup"}
)

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	//private static final String root = "/semi-compilado";
	
	public void init() {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println("GET LOGIN");

		
			switch (action) {
			case "/login":
				showNewForm(request, response);
				break;
			default:
				showNewForm(request, response);
				break;
			}
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("FORM DE LOGIN");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		
		//response.sendRedirect("../");
		dispatcher.forward(request, response);
	}
	
	private void showHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-home.jsp");
		dispatcher.forward(request, response);
	}
}