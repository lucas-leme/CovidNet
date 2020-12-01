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
import net.javaguides.usermanagement.dao.PacienteDAO;
import net.javaguides.usermanagement.dao.ProntuarioDAO;
import net.javaguides.usermanagement.model.Hospital;
import net.javaguides.usermanagement.model.Paciente;
import net.javaguides.usermanagement.model.Prontuario;

/**
 * prontuarioServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Hugo Martins
 */

@WebServlet(
urlPatterns = {"/prontuarios","/prontuarios/edit","/prontuarios/update/*", "/prontuarios/new", "/prontuarios/insert",
				"/pacientes", "/pacientes/new", "/pacientes/insert", "/prontuarios/new_paciente", "/prontuarios/exame"}
)
public class ProntuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProntuarioDAO prontuarioDAO;
	private PacienteDAO pacienteDAO;
	private HospitalDAO hospitalDAO;
	private static final String root = "/semi-compilado";

	public void init() {
		prontuarioDAO = new ProntuarioDAO();
		pacienteDAO = new PacienteDAO();
		hospitalDAO = new HospitalDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Doing 'post'");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			
			System.out.println("acao: " + action);
			
			switch (action) {
				case "/prontuarios/new":
					showNewForm(request, response);
					break;
				case "/prontuarios/new_paciente":
					showNewPacienteForm(request, response);
					break;
				case "/prontuarios/insert":
					insertProntuario(request, response);
					break;
				case "/prontuarios/edit":
					showEditForm(request, response);
					break;
				case "/prontuarios/update":
					updateProntuario(request, response);
					break;					
				case "/pacientes/new":
					showPacienteForm(request, response);
					break;
				case "/pacientes/insert":
					System.out.println("inserting");
					insertPaciente(request, response);
					break;
				case "/prontuarios/exame":
					newExame(request, response);
					break;
					
				case "prontuarios/list":
					System.out.println("LIST PRONTUARIOS");
					listProntuarios(request, response);
					break;
				
				default:
					System.out.println("DEFAULT");
					showMainPage(request, response);

			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void showMainPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prontuario-home.jsp");
		dispatcher.forward(request, response);
	}
	
	private void newExame(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/form-exame.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewPacienteForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/form-paciente.jsp");
		dispatcher.forward(request, response);
	}
	// AQUI TEMOS QUE FAZER AS DUAS FUNCOES VIRAREM UMA SO
	private void showPacienteForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Paciente form");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/paciente-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listProntuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.println("Listing prontuarios");
		
		List<Prontuario> listProntuarios = null;//prontuarioDAO.selectAllProntuarios();
		request.setAttribute("listProntuarios", listProntuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prontuario-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("New form prontuario");
		System.out.println("id do paciente existe? : " + request.getParameter("id_paciente"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prontuario-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.out.println("editing form prontuario");
		
		//int id = Integer.parseInt(request.getParameter("id"));
		Prontuario existingProntuario = prontuarioDAO.selectProntuarioByPacienteCpf("111.111.111-11");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prontuario-form.jsp");
		
		request.setAttribute("prontuario", existingProntuario);
		dispatcher.forward(request, response);
	}

	private void insertPaciente(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			System.out.println("inserting paciente");
			
			String cpf = request.getParameter("cpf");
			String nome = request.getParameter("nome");
			String data_de_nascimento = request.getParameter("data_de_nascimento");  
			String endereco = request.getParameter("endereco");
			
			System.out.println("cpf: " + cpf + "; nome: " + nome + "; nascimento: " + data_de_nascimento + "; endereco: " + endereco);
			
			Paciente newPaciente = new Paciente(cpf, nome, data_de_nascimento, endereco);
			int id_paciente = pacienteDAO.insertPaciente(newPaciente);
			newPaciente.setId(id_paciente);
			System.out.println("id do paciente adicionado: " + id_paciente);
		
			
			System.out.println("selecionando hospital cujo id e 1");
			List<Hospital> hospitais = hospitalDAO.selectAllHospitais();//hospitalDAO.selectHospitais(1); // MUDAR PRA VARIOS IDS

			request.setAttribute("id_paciente", id_paciente);	
			request.setAttribute("hospitais", hospitais);	
			request.setAttribute("cpf", cpf);	
			request.setAttribute("nome", nome);	
			request.setAttribute("data_de_nascimento", data_de_nascimento);
			
			System.out.println("Novo jsp: prontuario");
			//System.out.println("redirecting to " + root + "/prontuarios/new");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/prontuario-form.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect(root + "/prontuarios/new");
		}
	
	private boolean checkBool (String choosenAtribute) {
		
		if (choosenAtribute == "Sim") {
			return true;
		} else {
			return false;
		}
	}

	private void insertProntuario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("\n\n\ninseting prontuario");
		System.out.println("Params");
		System.out.println(request.getAttribute("id_paciente"));
		System.out.println(request.getParameter("id_paciente"));
		
		String data = request.getParameter("data");
		String estado_do_paciente = request.getParameter("estado_paciente");
		String diagnostico = request.getParameter("diagnostico");
		String teste_covid = request.getParameter("teste_covid");
		boolean doenca_respiratoria = checkBool(request.getParameter("doenca_respiratoria"));
		boolean batimento_cardiaco_normal = checkBool(request.getParameter("batimento_cardiaco_normal"));
		boolean hipertensao = checkBool(request.getParameter("batimento_cardiaco_normal"));
		int oximetria = Integer.parseInt(request.getParameter("oximetria"));
		boolean tomografia_torax_normal = checkBool(request.getParameter("tomografia_torax_normal"));
		boolean ventilacao_mecanica = checkBool(request.getParameter("ventilacao_mecanica"));
		boolean diabetes = checkBool(request.getParameter("diabetes"));
		boolean obesidade = checkBool(request.getParameter("obesidade"));
		boolean ativo = true;
		boolean radiometria_torax_normal = checkBool(request.getParameter("radiometria_torax_normal"));
		int hospital_id = Integer.parseInt(request.getParameter("hospital_id"));
		int hospital_destino_id = -1; // TODO
		int paciente_id = Integer.parseInt(request.getParameter("id_paciente"));
				


		Prontuario newProntuario = new Prontuario(data, estado_do_paciente, diagnostico, teste_covid, 
				doenca_respiratoria, batimento_cardiaco_normal, hipertensao, oximetria,  radiometria_torax_normal,
				tomografia_torax_normal, ventilacao_mecanica, diabetes, obesidade, ativo, hospital_id, hospital_destino_id,
				paciente_id);
		
		prontuarioDAO.insertProntuario(newProntuario, hospital_id, paciente_id);
		
		//System.out.println("insertPorntuario : redirecting to :" + root + "/prontuarios");
		response.sendRedirect(root + "/prontuarios");
	}

	private void updateProntuario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("updating prontuario");
		int id;
		String cpf, nome, data_de_nascimento, data_de_entrada; 
		String nome_exame, descricao_exame, data_exame, resultado_exame; 
		
		if(request.getParameter("add_paciente") != null) {
			System.out.println("Adicionando paciente");
			
			id = Integer.parseInt(request.getParameter("id"));
			cpf = request.getParameter("cpf");
			nome = request.getParameter("nome");
			data_de_nascimento = request.getParameter("data_de_nascimento");  
			data_de_entrada = request.getParameter("data_de_entrada"); 
			nome_exame = request.getParameter("nome_exame"); 
			descricao_exame = request.getParameter("descricao_exame"); 
			data_exame = request.getParameter("data_exame"); 
			resultado_exame = request.getParameter("resultado_exame"); 
		}else {
		}

		Prontuario book = new Prontuario(id, cpf, nome ,data_de_nascimento, data_de_entrada, nome_exame, descricao_exame, data_exame, resultado_exame);
		
		prontuarioDAO.updateProntuario(book);
		response.sendRedirect(root + "/prontuarios");
	}
}
