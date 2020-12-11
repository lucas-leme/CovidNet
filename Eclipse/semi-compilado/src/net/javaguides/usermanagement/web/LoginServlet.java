package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	private boolean logged;
	
	private static final String root = "/semi-compilado";
	
	public void init() 
	{
		loginDAO = new LoginDAO();
		//logged = false;
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
		
		//if(!logged) request.setAttribute("validate", true);
		//request.setAttribute("logged", true);
		//request.setAttribute("validate", true);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
		
		//System.out.println("Se isso aqui so printar depoisque apertar obotao, tudo certo");
	}

	private void doSignin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String psswd = request.getParameter("psswd");
		
		System.out.println("\nemail: " + email + "; senha: " + psswd);
		
		String cargo = loginDAO.loginUsuario(email, psswd);
		
		if(cargo != null)
		{
			System.out.println("\nFazendo o signin");
			//logged = true;
			
			//request.setAttribute("logged", true);

	    	Cookie eBom = new Cookie("autorizacao", "medico");
	    	//eBom.setMaxAge(); // meia hora
	    	response.addCookie(eBom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/homePage.jsp");
			dispatcher.forward(request, response);
		}
		else 
		{			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		//response.sendRedirect(root + "/");
	}
	
}