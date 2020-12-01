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
import net.javaguides.usermanagement.dao.PacienteDAO;
import net.javaguides.usermanagement.dao.ProntuarioDAO;
import net.javaguides.usermanagement.model.FilaDePaciente;
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
urlPatterns = { "/prontuarios","/prontuarios/edit","/prontuarios/update/*", "/prontuarios/new", "/prontuarios/insert",
				"/pacientes", "/pacientes/new", "/pacientes/insert", "/prontuarios/new_paciente", "/prontuarios/exame",
				"/prontuarios/search", "/prontuarios/close", "/prontuarios/solicitar_uti" }
)
public class ProntuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProntuarioDAO prontuarioDAO;
	private PacienteDAO pacienteDAO;
	private HospitalDAO hospitalDAO;
	private FilaDePacienteDAO filaDAO;
	private static final String root = "/semi-compilado";

	public void init() {
		prontuarioDAO = new ProntuarioDAO();
		pacienteDAO = new PacienteDAO();
		hospitalDAO = new HospitalDAO();
		
		filaDAO = new FilaDePacienteDAO();
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
					System.out.println("Updating");
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
					
				case "/prontuarios/search":
					System.out.println("SEARCH PRONTUARIO");
					searchProntuarios(request, response);
					break;
					
				case "/prontuarios/close":
					closeProntuario(request, response);
					break;
					
				case "/prontuarios/solicitar_uti":
					solicitarUti(request, response);
					break;
				
				default:
					System.out.println("DEFAULT");
					showMainPage(request, response);

			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void searchProntuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		String cpf = request.getParameter("cpf");
		request.setAttribute("cpf", cpf);
		
		Prontuario prontuario = prontuarioDAO.selectProntuarioByPacienteCpf(cpf);
		
		if(prontuario != null)
		{
			System.out.println("id do prontuario: " + prontuario.getId());
			request.setAttribute("prontuario", prontuario);
	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/prontuario-info.jsp");
			dispatcher.forward(request, response);
		}else
		{
			System.out.println("nao achou o cpf");
			// ERRO - nao achou esse cpf
		}
	}
	
	private void closeProntuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		System.out.println("prontuario: " + request.getParameter("id_prontuario"));
		//Prontuario prontuario = (Prontuario) request.getAttribute("prontuario");
		//System.out.println("Fechando o prontuario: " + prontuario);
		
		int id_prontuario = Integer.parseInt(request.getParameter("id_prontuario"));
		request.setAttribute("id_prontuario", id_prontuario);
		
		prontuarioDAO.closeProntuario(id_prontuario);

		response.sendRedirect(root + "/prontuarios"); // Volta pra tela de pesquisa
	}
	
	private void solicitarUti(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id_prontuario = Integer.parseInt(request.getParameter("id_prontuario2"));
		System.out.println("id_prontuario: " + id_prontuario);
		
		
		filaDAO.solicitaUti(id_prontuario);
		List<FilaDePaciente> fila = filaDAO.selectAllPacientesNaFila();
		request.setAttribute("fila_pacientes", fila);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/prontuario-list.jsp");
		dispatcher.forward(request, response);
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
			
			
			System.out.println("Verificando atributos");

			System.out.println("iscpfok? : " + Solver.formatCpf(cpf));			


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
	
	private boolean checkBool (String chosenAttribute) {
		
		
		if (chosenAttribute.equals("Sim")) {
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
		
		//String data = request.getParameter("data");
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
		int paciente_id = Integer.parseInt(request.getParameter("id_paciente"));
		
		System.out.println("\n\nVENTILACAO MECANICA" + ventilacao_mecanica);
	
		Prontuario newProntuario = new Prontuario(
				estado_do_paciente,
				diagnostico,
				teste_covid,
				doenca_respiratoria,
				batimento_cardiaco_normal,
				hipertensao,
				oximetria,
				radiometria_torax_normal,
				tomografia_torax_normal,
				ventilacao_mecanica,
				diabetes,
				obesidade,
				ativo,
				hospital_id,
				paciente_id
			);
		
		prontuarioDAO.insertProntuario(newProntuario, hospital_id, paciente_id);
		
		response.sendRedirect(root + "/prontuarios");
	}

	private void updateProntuario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("updating prontuario");
	
		int id;
		String data;
		String estado_do_paciente;
		String diagnostico;
		String teste_covid;
		boolean doenca_respiratoria;
		boolean batimento_cardiaco_normal;
		boolean hipertensao;
		int oximetria;
		boolean radiometria_torax_normal;
		boolean tomografia_torax_normal;
		boolean ventilacao_mecanica;
		boolean diabetes;
		boolean obesidade;
		boolean ativo;
		int hospital_id;
		int hospital_destino_id;


		System.out.println("Adicionando paciente");
		
		id = Integer.parseInt(request.getParameter("id"));
		data = request.getParameter("data");
		estado_do_paciente = request.getParameter("estado_do_paciente");
		diagnostico = request.getParameter("diagnostico");
		teste_covid = request.getParameter("teste_covid");
		doenca_respiratoria = Boolean.parseBoolean(request.getParameter("doenca_respiratoria"));
		batimento_cardiaco_normal = Boolean.parseBoolean(request.getParameter("batimento_cardiaco_normal"));
		hipertensao = Boolean.parseBoolean(request.getParameter("hipertensao"));
		oximetria = Integer.parseInt(request.getParameter("oximetria"));
		radiometria_torax_normal = Boolean.parseBoolean(request.getParameter("radiometria_torax_normal"));
		tomografia_torax_normal = Boolean.parseBoolean(request.getParameter("tomografia_torax_normal"));
		ventilacao_mecanica = Boolean.parseBoolean(request.getParameter("ventilacao_mecanica"));
		diabetes = Boolean.parseBoolean(request.getParameter("diabetes"));
		obesidade = Boolean.parseBoolean(request.getParameter("obesidade"));
		ativo = Boolean.parseBoolean(request.getParameter("ativo"));
		hospital_id = Integer.parseInt(request.getParameter("hospital_id"));
		hospital_destino_id = Integer.parseInt(request.getParameter("hospital_destino_id"));
		
		Prontuario book = new Prontuario(
				id,
				data,
				estado_do_paciente,
				diagnostico,
				teste_covid,
				doenca_respiratoria,
				batimento_cardiaco_normal,
				hipertensao,
				oximetria,
				radiometria_torax_normal,
				tomografia_torax_normal,
				ventilacao_mecanica,
				diabetes,
				obesidade,
				ativo,
				hospital_id,
				hospital_destino_id
			);

		
		//prontuarioDAO.updateProntuario(book);
		response.sendRedirect(root + "/prontuarios");
	}
}
