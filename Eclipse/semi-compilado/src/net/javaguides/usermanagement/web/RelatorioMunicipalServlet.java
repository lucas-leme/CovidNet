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

import net.javaguides.usermanagement.dao.RelatorioMunicipalDAO;
//import net.javaguides.usermanagement.dao.RelatorioHospitalarDAO;
//import net.javaguides.usermanagement.dao.RelatorioMunicipalDAO;
import net.javaguides.usermanagement.model.RelatorioMunicipal;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet("/relatorioMunicipal")
public class RelatorioMunicipalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RelatorioMunicipalDAO RelatorioMunicipalDAO;
//	private RelatorioMunicipalDAO relatorioMunicipalDAO;
//	private RelatorioHospitalarDAO relatorioHospitalarDAO;
	
	public void init() {
		RelatorioMunicipalDAO = new RelatorioMunicipalDAO();
//		relatorioMunicipalDAO = new RelatorioMunicipalDAO();
//		relatorioHospitalarDAO = new RelatorioHospitalarDAO();
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
			case "/relatorioMunicipal/new":
				showNewForm(request, response);
				break;
			case "/relatorioMunicipal/insert":
				insertRelatorioMunicipal(request, response);
				break;
			case "/relatorioMunicipal/delete":
				deleteRelatorioMunicipal(request, response);
				break;
			case "/relatorioMunicipal/edit":
				showEditForm(request, response);
				break;
			case "/relatorioMunicipal/update":
				updateRelatorioMunicipal(request, response);
				break;
			default:
				listRelatorioMunicipal(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listRelatorioMunicipal(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<RelatorioMunicipal> listRelatorioMunicipal = RelatorioMunicipalDAO.selectAllRelatorios();
		request.setAttribute("listRelatorioMunicipal", listRelatorioMunicipal);
		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-municipal-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-municipal-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("relatorio_id"));
		RelatorioMunicipal existingRelatorioMunicipal = RelatorioMunicipalDAO.selectRelatorio(id);
		System.out.println("Rel: ");
		System.out.println(existingRelatorioMunicipal);
		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-municipal-form.jsp");
		request.setAttribute("relatorio", existingRelatorioMunicipal);
		dispatcher.forward(request, response);

	}

	private void insertRelatorioMunicipal(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
//		int id = Integer.parseInt(request.getParameter("id"));
		String nomeMunicipio = request.getParameter("nome_municipio");
		int numeroHospitais = Integer.parseInt(request.getParameter("numero_hospitais"));
		
		RelatorioMunicipal newRelatorio = new RelatorioMunicipal(0, nomeMunicipio, numeroHospitais);
		RelatorioMunicipalDAO.insertRelatorio(newRelatorio);
		response.sendRedirect("list");
	}

	private void updateRelatorioMunicipal(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("relatorio_id"));
		String nomeMunicipio = request.getParameter("nome_municipio");
		int numeroHospitais = Integer.parseInt(request.getParameter("numero_hospitais"));

		RelatorioMunicipal book = new RelatorioMunicipal(id, nomeMunicipio, numeroHospitais);

		RelatorioMunicipalDAO.updateRelatorio(book);
		response.sendRedirect("list");
	}

	private void deleteRelatorioMunicipal(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("relatorio_id"));
		RelatorioMunicipalDAO.deleteRelatorio(id);
		response.sendRedirect("list");

	}

}
