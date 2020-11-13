package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import net.javaguides.usermanagement.dao.PacienteDAO;
import net.javaguides.usermanagement.model.Leito;
import net.javaguides.usermanagement.model.Paciente;*/

/**
 * pacienteServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Hugo Martins
 */

@WebServlet("/")
public class MainPageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	//private PacienteDAO pacienteDAO;
	
	public void init() 
	{
		//pacienteDAO = new PacienteDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		//String action = request.getServletPath();
		request.setCharacterEncoding("UTF-8");

		try {
			showMainPage(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void showMainPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		System.out.println("Showing main page");
		
		System.out.println("\nPath: " + request.getContextPath());
		System.out.println("\nURI: " + request.getRequestURI());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("homePage.jsp");
		
		dispatcher.forward(request, response);
		
		//System.out.println("Sera que o bug e na linha de cima?"); spoilers: nao e
		
	}
	
}