//package net.javaguides.usermanagement.web;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.javaguides.usermanagement.dao.RelatorioEstadualDAO;
////import net.javaguides.usermanagement.dao.RelatorioHospitalarDAO;
////import net.javaguides.usermanagement.dao.RelatorioMunicipalDAO;
//import net.javaguides.usermanagement.model.RelatorioEstadual;
//
///**
// * ControllerServlet.java
// * This servlet acts as a page controller for the application, handling all
// * requests from the user.
// * @email Ramesh Fadatare
// */
//
//@WebServlet("/relatorioEstadual")
//public class RelatorioHospitalarServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private RelatorioEstadualDAO relatorioEstadualDAO;
////	private RelatorioMunicipalDAO relatorioMunicipalDAO;
////	private RelatorioHospitalarDAO relatorioHospitalarDAO;
//	
//	public void init() {
//		relatorioEstadualDAO = new RelatorioEstadualDAO();
////		relatorioMunicipalDAO = new RelatorioMunicipalDAO();
////		relatorioHospitalarDAO = new RelatorioHospitalarDAO();
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getServletPath();
//
//		try {
//			switch (action) {
//			case "/relatorioEstadual/new":
//				showNewForm(request, response);
//				break;
//			case "/relatorioEstadual/insert":
//				insertRelatorioEstadual(request, response);
//				break;
//			case "/relatorioEstadual/delete":
//				deleteRelatorioEstadual(request, response);
//				break;
//			case "/relatorioEstadual/edit":
//				showEditForm(request, response);
//				break;
//			case "/relatorioEstadual/update":
//				updateRelatorioEstadual(request, response);
//				break;
//			default:
//				listRelatorioEstadual(request, response);
//				break;
//			}
//		} catch (SQLException ex) {
//			throw new ServletException(ex);
//		}
//	}
//
//	private void listRelatorioEstadual(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//		List<RelatorioEstadual> listRelatorioEstadual = relatorioEstadualDAO.selectAllRelatorios();
//		request.setAttribute("listRelatorioEstadual", listRelatorioEstadual);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-list.jsp");
//		dispatcher.forward(request, response);
//	}
//
//	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-estadual-form.jsp");
//		dispatcher.forward(request, response);
//	}
//
//	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, ServletException, IOException {
//		
//		int id = Integer.parseInt(request.getParameter("id"));
//		RelatorioEstadual existingRelatorioEstadual = relatorioEstadualDAO.selectRelatorio(id);
//		System.out.println("Rel: ");
//		System.out.println(existingRelatorioEstadual);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio-estadual-form.jsp");
//		request.setAttribute("relatorio", existingRelatorioEstadual);
//		dispatcher.forward(request, response);
//
//	}
//
//	private void insertRelatorioEstadual(HttpServletRequest request, HttpServletResponse response) 
//			throws SQLException, IOException {
//		
////		int id = Integer.parseInt(request.getParameter("id"));
//		String nomeEstado = request.getParameter("nomeEstado");
//		int numeroMunicipios = Integer.parseInt(request.getParameter("numeroMunicipios"));
//		int numeroHospitais = Integer.parseInt(request.getParameter("numeroHospitais"));
//		
//		RelatorioEstadual newRelatorio = new RelatorioEstadual(0, nomeEstado, numeroMunicipios, numeroHospitais);
//		relatorioEstadualDAO.insertRelatorio(newRelatorio);
//		response.sendRedirect("list");
//	}
//
//	private void updateRelatorioEstadual(HttpServletRequest request, HttpServletResponse response) 
//			throws SQLException, IOException {
//		
//		int id = Integer.parseInt(request.getParameter("id"));
//		String nomeEstado = request.getParameter("nomeEstado");
//		int numeroMunicipios = Integer.parseInt(request.getParameter("numeroMunicipios"));
//		int numeroHospitais = Integer.parseInt(request.getParameter("numeroHospitais"));
//
//		RelatorioEstadual book = new RelatorioEstadual(id, nomeEstado, numeroMunicipios, numeroHospitais);
//
//		relatorioEstadualDAO.updateRelatorio(book);
//		response.sendRedirect("list");
//	}
//
//	private void deleteRelatorioEstadual(HttpServletRequest request, HttpServletResponse response) 
//			throws SQLException, IOException {
//		int id = Integer.parseInt(request.getParameter("id"));
//		relatorioEstadualDAO.deleteRelatorio(id);
//		response.sendRedirect("list");
//
//	}
//
//}
