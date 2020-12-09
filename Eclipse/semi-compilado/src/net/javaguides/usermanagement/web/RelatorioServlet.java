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

import net.javaguides.usermanagement.dao.RelatorioEstadualDAO;
//import net.javaguides.usermanagement.dao.RelatorioHospitalarDAO;
//import net.javaguides.usermanagement.dao.RelatorioMunicipalDAO;
import net.javaguides.usermanagement.model.RelatorioEstadual;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet(
		urlPatterns = {"/relatorios","/relatorios/edit","/relatorios/update/*", 
				"/relatorios/new", "/relatorios/insert", "/relatorios/delete"}
)
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RelatorioEstadualDAO relatorioEstadualDAO;
//	private RelatorioMunicipalDAO relatorioMunicipalDAO;
//	private RelatorioHospitalarDAO relatorioHospitalarDAO;
	private static final String root = "/semi-compilado";
	
	public void init() {
		relatorioEstadualDAO = new RelatorioEstadualDAO();
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
		System.out.println("Action: " + action);

		try {
			switch (action) {
	
			case "/relatorios/new":
				System.out.println("Novo relatorio estadual");
				createNewRelatorio(request, response);
				break;
			case "/relatorios/insert":
				insertRelatorio(request, response);
				break;
			case "/relatorios/delete":
				deleteRelatorio(request, response);
				break;
			case "/relatorios/edit":
				showEditForm(request, response);
				break;
			case "/relatorios/update":
				updateRelatorio(request, response);
				break;
			default:
				listRelatorio(request, response);
				break;
			}
		} catch (SQLException ex) {
			System.out.println("SQL excpetion : vc errou alguma coisa");
			throw new ServletException(ex);
		}
	}

	private void listRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<RelatorioEstadual> listRelatorioEstadual = relatorioEstadualDAO.selectAllRelatorios();
		request.setAttribute("listRelatorioEstadual", listRelatorioEstadual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
		dispatcher.forward(request, response);
	}

	private void createNewRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Novo relatorio");
		String dataInicio = request.getParameter("data_inicio");
		String dataFim = request.getParameter("data_inicio");
		String relatorioId = request.getParameter("tipo_relatorio");

		System.out.println("datainicio: " + dataInicio);
		System.out.println("datafim " + dataFim);
		System.out.println("relatorioid: " + relatorioId);

		request.setAttribute("data_inicio", dataInicio);
		request.setAttribute("rdata_fim", dataFim);
		request.setAttribute("relatorio_id", relatorioId);
		
		RequestDispatcher dispatcher = null;
		
		switch(relatorioId) {
			case("/semi-compilado/relatorioHospitalar"):
				
				dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
				break;
			case("/semi-compilado/relatorioMunicipal"):
				
				dispatcher = request.getRequestDispatcher("/relatorio-municipal-list.jsp");
				break;
			case("/semi-compilado/relatorioEstadual"):
				
				dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
				break;
			default:
				dispatcher = request.getRequestDispatcher("/relatorio-main-page.jsp");
				break;
		}
		// NESSE JSP VAI TER QUE TER SELECTS COM AS SOLICITACOES RELEVANTES
		// DEVE REDIRECIONAR PRA /relatorioEstadual/insert
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		RelatorioEstadual existingRelatorioEstadual = relatorioEstadualDAO.selectRelatorio(id);
		
		System.out.println("Rel (showeditform): ");
		System.out.println(existingRelatorioEstadual);
		System.out.println("id: " + id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/relatorio-estadual-form.jsp");
		request.setAttribute("relatorio", existingRelatorioEstadual);
		dispatcher.forward(request, response);

	}

	private void insertRelatorio(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		//int id = Integer.parseInt(request.getParameter("id"));
		//String nomeEstado = request.getParameter("nomeEstado"); -> o estado a gnt ja sabe => SP
		//int numeroMunicipios = Integer.parseInt(request.getParameter("numeroMunicipios"));
		//int numeroHospitais = Integer.parseInt(request.getParameter("numeroHospitais"));
		String dataInicial = request.getParameter("data_inicial");
		String dataFinal = request.getParameter("data_final");
		
		
		
		RelatorioEstadual newRelatorio = new RelatorioEstadual(dataInicial, dataFinal);
		relatorioEstadualDAO.insertRelatorio(newRelatorio);
		response.sendRedirect(root + "/relatorioEstadual");
	}

	private void updateRelatorio(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nomeEstado = request.getParameter("nomeEstado");
		int numeroMunicipios = Integer.parseInt(request.getParameter("numeroMunicipios"));
		int numeroHospitais = Integer.parseInt(request.getParameter("numeroHospitais"));
		
		System.out.println("\n\nUPDATE RELAS\n\n");
		System.out.println("ID: " + id);

		RelatorioEstadual book = new RelatorioEstadual(id, nomeEstado, numeroMunicipios, numeroHospitais);

		relatorioEstadualDAO.updateRelatorio(book);
		response.sendRedirect(root + "/relatorioEstadual");
	}

	private void deleteRelatorio(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Deleting relatorio estadual");
		relatorioEstadualDAO.deleteRelatorio(id);
		response.sendRedirect(root + "/relatorioEstadual");

	}

}
