package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagement.dao.HospitalDAO;
import net.javaguides.usermanagement.dao.RelatorioDAO;
import net.javaguides.usermanagement.model.Hospital;
import net.javaguides.usermanagement.model.Municipio;
import net.javaguides.usermanagement.model.Relatorio;
import net.javaguides.usermanagement.model.RelatorioUti;
import net.javaguides.usermanagement.model.RelatorioVagas;

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
				mainPageRelas(request, response);
				break;
			}
		} catch (SQLException ex) {
			System.out.println("SQL excpetion : vc errou alguma coisa");
			throw new ServletException(ex);
		}
	}

	private void mainPageRelas(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Hospital> hospitais = hospitalDAO.selectAllHospitais();
		List<Municipio> municipios = hospitalDAO.selectAllMunicipios();
		
		request.setAttribute("hospitais", hospitais);
		request.setAttribute("municipios", municipios);
		
		System.out.println("mainpage: " + hospitais + municipios);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/relatorios-main-page.jsp");
		dispatcher.forward(request, response);
	}
	
	private List<Float> calculaOcupacao(List<RelatorioVagas> vagas) {
		
		List<Float> ocupacaoHistorica =  new ArrayList<>();
		
		int nVagas = vagas.size();
		
		for (int i = 0; i < nVagas; i++) {
			RelatorioVagas vaga = vagas.get(i);
			
			float ocupacao =  100 * vaga.getVagasOcupadas() / (float) vaga.getVagasTotais();
			
			ocupacaoHistorica.add(ocupacao);
		}
		
		return ocupacaoHistorica;
	}


	private void createNewRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println("Novo relatorio");
		String dataInicio = request.getParameter("data_inicio");
		String dataFim = request.getParameter("data_fim");
		String relatorioId = request.getParameter("tipo_relatorio");

		System.out.println("datainicio: " + dataInicio);
		System.out.println("datafim " + dataFim);
		System.out.println("relatorioid: " + relatorioId);

		request.setAttribute("data_inicio", dataInicio);
		request.setAttribute("rdata_fim", dataFim);
		request.setAttribute("relatorio_id", relatorioId);
		
		RequestDispatcher dispatcher = null;
		List<RelatorioUti> alocs = null;			
		List<RelatorioUti> pedidos = null;
		List<RelatorioVagas> vagas = null;
		List<Float> ocupacao = null;
		
		switch(relatorioId) {
			case("rel_hospitalar"):
				System.out.println("hospitalar");

				int idHospital = Integer.parseInt(request.getParameter("which_element"));
				System.out.println("HOSPITAL: " + idHospital);
				
				//Relatorio relHosp = new Relatorio(dataInicio, dataFim);
				
				alocs = relatorioDAO.RelatorioUtiAlocacoesPorHospital(dataInicio, dataFim, idHospital);				
				pedidos = relatorioDAO.RelatorioUtiPedidosPorHospital(dataInicio, dataFim, idHospital);
				vagas = relatorioDAO.RelatorioVagasHospital(dataInicio, dataFim, idHospital);
				ocupacao = calculaOcupacao(vagas);
				
				request.setAttribute("alocs", alocs);
				request.setAttribute("pedidos", pedidos);
				request.setAttribute("vagas", vagas);
				request.setAttribute("ocupacao", ocupacao);
				
				dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
			
				break;
			case("rel_municipal"):
				System.out.println("municipal");

				int idMunicipio = Integer.parseInt(request.getParameter("which_element"));
				System.out.println("MUNICIPIO: " + idMunicipio);
				
				//Relatorio relHosp = new Relatorio(dataInicio, dataFim);
				
				alocs = relatorioDAO.RelatorioUtiAlocacoesPorMunicipio(dataInicio, dataFim, idMunicipio);				
				pedidos = relatorioDAO.RelatorioUtiPedidosPorMunicipio(dataInicio, dataFim, idMunicipio);
				vagas = relatorioDAO.RelatorioVagasMunicipio(dataInicio, dataFim, idMunicipio);
				ocupacao = calculaOcupacao(vagas);

				request.setAttribute("alocs", alocs);
				request.setAttribute("pedidos", pedidos);
				request.setAttribute("vagas", vagas);
				request.setAttribute("ocupacao", ocupacao);
				
				
				dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
				
				break;
			case("rel_estadual"):
				System.out.println("estadual");
				
				alocs = relatorioDAO.RelatorioUtiAlocacoesTotais(dataInicio, dataFim);				
				pedidos = relatorioDAO.RelatorioUtiPedidosTotais(dataInicio, dataFim);
				vagas = relatorioDAO.RelatorioVagasTotais(dataInicio, dataFim);
				ocupacao = calculaOcupacao(vagas);

				request.setAttribute("alocs", alocs);
				request.setAttribute("pedidos", pedidos);
				request.setAttribute("vagas", vagas);
				request.setAttribute("ocupacao", ocupacao);
				
				dispatcher = request.getRequestDispatcher("/relatorio-estadual-list.jsp");
				
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
