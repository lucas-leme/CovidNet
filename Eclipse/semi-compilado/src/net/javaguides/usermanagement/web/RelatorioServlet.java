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

import net.javaguides.usermanagement.dao.HospitalDAO;
import net.javaguides.usermanagement.dao.RelatorioDAO;
import net.javaguides.usermanagement.model.Relatorio;

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
public class RelatorioServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private RelatorioDAO relatorioDAO;
	private HospitalDAO hospitalDAO;
	
	private static final String root = "/semi-compilado";
	
	public void init() {
		relatorioDAO = new RelatorioDAO();
		hospitalDAO = new HospitalDAO();
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
		
		//List<Relatorio> listRelatorio = relatorioDAO.selectAllRelatorios();
		//request.setAttribute("listRelatorioEstadual", listRelatorioEstadual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
		dispatcher.forward(request, response);
	}

	private void createNewRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
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
				System.out.println("/semi-compilado/relatorioHospitalar");
				
				//Relatorio relHosp = new Relatorio(dataInicio, dataFim);
				//relatorioDAO.RelatorioUtiAlocacoesPorHospital(data_inicio, data_fim, hospital_destino_id)
				System.out.println("AINDA NAO DISPONIVEL");
				
				dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
				System.out.println("JSP ESTADUAL APARECEU NA TELA");
			
				break;
			case("/semi-compilado/relatorioMunicipal"):

				System.out.println("/semi-compilado/relatoriomunicipal");
				
				Relatorio relMuni = new Relatorio(dataInicio, dataFim);
				//relatorioDAO.
				
				dispatcher = request.getRequestDispatcher("/relatorio-municipal-list.jsp");
				System.out.println("JSP ESTADUAL APARECEU NA TELA");
				
				break;
			case("/semi-compilado/relatorioEstadual"):
				System.out.println("/semi-compilado/relatorioEstadual");
				
				Relatorio relEstad = new RelatorioEstadual(dataInicio, dataFim);
				//relatorioDAO.insertRelatorio(relEstad);
				
				dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
				System.out.println("JSP ESTADUAL APARECEU NA TELA");
				
				break;
			default:
				System.out.println("DEFAULT");
				dispatcher = request.getRequestDispatcher("/relatorio-main-page.jsp");
				break;
		}
		
		System.out.println("DEPOIS DO SWITCH");
		
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Relatorio existingRelatorioEstadual = null;//relatorioEstadualDAO.selectRelatorio(id);
		
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
		
		
		
		Relatorio newRelatorio = null;//new RelatorioEstadual(dataInicial, dataFinal);
		//relatorioEstadualDAO.insertRelatorio(newRelatorio);
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

		Relatorio book = null;//new RelatorioEstadual(id, nomeEstado, numeroMunicipios, numeroHospitais);

		//relatorioEstadualDAO.updateRelatorio(book);
		response.sendRedirect(root + "/relatorioEstadual");
	}

	private void deleteRelatorio(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Deleting relatorio estadual");
		//relatorioEstadualDAO.deleteRelatorio(id);
		response.sendRedirect(root + "/relatorioEstadual");

	}

}
