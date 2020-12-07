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

/*import net.javaguides.usermanagement.dao.ProntuarioDAO;
import net.javaguides.usermanagement.model.Leito;
import net.javaguides.usermanagement.model.Prontuario;*/

/**
 * rontuarioServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Hugo Martins
 */

@WebServlet("/")
public class MainPageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	//private ProntuarioDAO prontuarioDAO;
	
	public void init() 
	{
		//prontuarioDAO = new ProntuarioDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		//String action = request.getServletPath();
		request.setCharacterEncoding("UTF-8");

		System.out.println("MAIN PAGE SERVLET, AQUI E PROIBIDO");
		
		/*try {
			showMainPage(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}*/
	}

	
}