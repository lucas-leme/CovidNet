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
import net.javaguides.usermanagement.dao.PacienteDAO;
import net.javaguides.usermanagement.model.Hospital;
import net.javaguides.usermanagement.model.Leito;
import net.javaguides.usermanagement.model.Municipio;
import net.javaguides.usermanagement.model.Paciente;


@WebServlet(
  urlPatterns = {"/leitos","/leitos/edit","/leitos/update/*", "/leitos/new", 
		  "/leitos/insert", "/leitos/list_hospitais", "/leitos/list_leitos", "/leitos/home",
		  "/leitos/alocar", "/leitos/alocar/primeiro"}
  )
public class LeitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeitoDAO leitoDAO;
	private HospitalDAO hospitalDAO;
	private PacienteDAO pacienteDAO;
	private FilaDePacienteDAO filaDePacienteDao;
	private static final String root = "/semi-compilado";
	
	public void init() {
		leitoDAO = new LeitoDAO();
		hospitalDAO = new HospitalDAO();
		filaDePacienteDao = new FilaDePacienteDAO();
		pacienteDAO = new PacienteDAO();
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
			case "/leitos/new":
				showNewForm(request, response);
				break;
			case "/leitos/home":
				showHome(request, response);
				break;
			case "/leitos/edit":
				showEditForm(request, response);
				break;
			case "/leitos/update":

				//System.out.println("\nPedindo GET update");
				updateLeito(request, response);
				break;
			case "/leitos/list_hospitais":
				mostrarHospitais(request, response);
				break;
			case "/leitos/list_leitos":
				listLeito(request, response);
				break;
			case "/leitos/alocar/primeiro":
				System.out.println("Alocando primeiro paciente");
				insertFirstProntuario(request, response);
				break;
			case "/leitos/alocar":
				System.out.println("ALOCANDO");
				allocatePaciente(request, response);
				break;
			default:
				showHome(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listLeito(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Leito> listLeito = leitoDAO.selectAllLeitos();
		request.setAttribute("listLeito", listLeito);
		
		//System.out.println("\nPROBLEMA AQUI");
		//System.out.println("\nPath: " + request.getContextPath());
		//System.out.println("\nQuery: " + request.getQueryString());
		//System.out.println("\nURI: " + request.getRequestURI());
		//System.out.println("Procurando o JSP do leito (Servlet:listleito)");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-list.jsp");

		
		//response.sendRedirect("..");
		dispatcher.forward(request, response);
	}
	
	private void insertFirstProntuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.println("Inserindo Primeiro Paciente");
		
		Paciente primeiroPaciente = filaDePacienteDao.selectPrimeiroPacienteDaFila();
		request.setAttribute("paciente", primeiroPaciente);
		
		System.out.println("PRIMEIRO PACIENTE " + primeiroPaciente.getId());
		
		int hospital_id = pacienteDAO.selectHospitalDeDestinoId(primeiroPaciente.getId());
		Hospital hospital = hospitalDAO.selectHospitalById(hospital_id);
		
		System.out.println("hospital nome " +  hospital.getNome());
		request.setAttribute("hospital", hospital);
		
		
		List<Municipio> cidades = hospitalDAO.selectAllMunicipios();
		request.setAttribute("cidades", cidades);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-filtros.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrarHospitais(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		//List<Leito> listLeito = leitoDAO.selectAllLeitos();
		
		String cidade = request.getParameter("cidade").toString();
		int paciente_id = Integer.parseInt(request.getParameter("paciente_id"));
		
		System.out.println(cidade + paciente_id);

		System.out.println("Cidade (mostrarHospitais) : " + cidade);
		List<Hospital> hospitais = hospitalDAO.selectHospitais(Integer.parseInt(cidade));//.selectHospitais(cidade);

		request.setAttribute("hospitais", hospitais);
		
		Paciente paciente = pacienteDAO.selectPacienteById(paciente_id);
		request.setAttribute("paciente", paciente);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-hospitais.jsp");

		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Procurando o JSP do leito (Servlet:shownewform)");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-form.jsp");

		//response.sendRedirect("../");
		dispatcher.forward(request, response);
	}
	
	private void showHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-home.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Leito existingLeito = leitoDAO.selectLeitoById(id);
		
		System.out.println("Procurando o JSP do leito (Servlet:showeditform)");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-form.jsp");
		request.setAttribute("leito", existingLeito);
		
		//response.sendRedirect("..");
		dispatcher.forward(request, response);

	}
	
	private void allocatePaciente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		int hospital_id = Integer.parseInt(request.getParameter("hospital_id"));
		int paciente_id = Integer.parseInt(request.getParameter("paciente_id"));
		
		filaDePacienteDao.alocaPrimeiroPacienteDaFila(paciente_id , hospital_id);
		leitoDAO.ocupaLeitoAleatorio(hospital_id, paciente_id);
		
		response.sendRedirect(root);

	}

	private void updateLeito(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		//System.out.println("\nUPDATE LEITO");
		//System.out.println(request.getParameterValues(""));
		//System.out.println(request.getParameter("salvar"));
		//System.out.println(request.getParameter("liberar"));
		int id = Integer.parseInt(request.getParameter("id"));
		String medico, enfermeiro, paciente;
		
		if(request.getParameter("liberar") == null) {
			//System.out.println("Nao liberar");
			medico = request.getParameter("medico");
			enfermeiro = request.getParameter("enfermeiro");
			paciente = request.getParameter("paciente");
		}else {
			//System.out.println("Liberar");
			medico = enfermeiro = paciente = "-";
		}
		
		//System.out.println("AGora liberou / salvou");

		//Leito book = new Leito(id, medico, enfermeiro, paciente);
		//leitoDAO.updateLeito(book);
		
		//System.out.println("Redirecionando para a lista");
		response.sendRedirect(root + "/leitos");
		//response.forward();
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/leito-list.jsp");
		//response.sendRedirect("..");
		//dispatcher.forward(request, response);
	}
}