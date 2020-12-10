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
import net.javaguides.usermanagement.dao.LoginDAO;
import net.javaguides.usermanagement.dao.PacienteDAO;
import net.javaguides.usermanagement.model.Hospital;
import net.javaguides.usermanagement.model.Leito;
import net.javaguides.usermanagement.model.Municipio;
import net.javaguides.usermanagement.model.Paciente;


@WebServlet(
  urlPatterns = {"/login/signin", "/login"}
)

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDAO loginDAO;
	
	private static final String root = "/semi-compilado";
	
	public void init() 
	{
		loginDAO = new LoginDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println("GET LOGIN");
		
			switch (action)
			{
				case "/login/signin":
					doSignin(request, response);
					break;
				default:
					showFormSignin(request, response);
					break;
			}
	}

	private void showFormSignin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("\nFORM DE SIGNIN");
		
		request.setAttribute("validate", true);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	private void doSignin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String psswd = request.getParameter("psswd");
		
		System.out.println("\nemail: " + email + "; senha: " + psswd);
		
		if(true)
		{
			System.out.println("\nFazendo o signin");
			request.setAttribute("logged", true);
		}
		
		response.sendRedirect(root + "/");
	}
	
}