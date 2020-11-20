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

import net.javaguides.usermanagement.dao.RelatorioHospitalarDAO;
//import net.javaguides.usermanagement.dao.RelatorioHospitalarDAO;
//import net.javaguides.usermanagement.dao.RelatorioMunicipalDAO;
import net.javaguides.usermanagement.model.RelatorioHospitalar;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet("/relatorioHospitalar")
public class RelatorioHospitalarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RelatorioHospitalarDAO RelatorioHospitalarDAO;
//	private RelatorioMunicipalDAO relatorioMunicipalDAO;
//	private RelatorioHospitalarDAO relatorioHospitalarDAO;
	
	public void init() {
		RelatorioHospitalarDAO = new RelatorioHospitalarDAO();
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
			case "/RelatorioHospitalar/new":
				showNewForm(request, response);
				break;
			case "/RelatorioHospitalar/insert":
				insertRelatorioHospitalar(request, response);
				break;
			case "/RelatorioHospitalar/delete":
				deleteRelatorioHospitalar(request, response);
				break;
			case "/RelatorioHospitalar/edit":
				showEditForm(request, response);
				break;
			case "/RelatorioHospitalar/update":
				updateRelatorioHospitalar(request, response);
				break;
			default:
				listRelatorioHospitalar(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listRelatorioHospitalar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<RelatorioHospitalar> listRelatorioHospitalar = RelatorioHospitalarDAO.selectAllRelatorios();
		request.setAttribute("listRelatorioHospitalar", listRelatorioHospitalar);
		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-hospitalar-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-hospitalar-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		RelatorioHospitalar existingRelatorioHospitalar = RelatorioHospitalarDAO.selectRelatorio(id);
		System.out.println("Rel: ");
		System.out.println(existingRelatorioHospitalar);
		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-hospitalar-form.jsp");
		request.setAttribute("relatorio", existingRelatorioHospitalar);
		dispatcher.forward(request, response);

	}

	private void insertRelatorioHospitalar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
//		int id = Integer.parseInt(request.getParameter("id"));
		String nomeMunicipio = request.getParameter("nome_municipio");
		
		RelatorioHospitalar newRelatorio = new RelatorioHospitalar(0, nomeMunicipio);
		RelatorioHospitalarDAO.insertRelatorio(newRelatorio);
		response.sendRedirect("list");
	}

	private void updateRelatorioHospitalar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("relatorio_id"));
		String nomeMunicipio = request.getParameter("nome_municipio");

		RelatorioHospitalar book = new RelatorioHospitalar(id, nomeMunicipio);

		RelatorioHospitalarDAO.updateRelatorio(book);
		response.sendRedirect("list");
	}

	private void deleteRelatorioHospitalar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		RelatorioHospitalarDAO.deleteRelatorio(id);
		response.sendRedirect("list");

	}

}
