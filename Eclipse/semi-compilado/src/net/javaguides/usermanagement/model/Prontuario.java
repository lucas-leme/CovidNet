package net.javaguides.usermanagement.model;

/**
 * Prontuario.java
 * This is a model class represents a Prontuario entity
 * @author Hugo Martins
 *
 */

public class Prontuario {
	protected int id;
	protected String data;
	protected String estado_do_paciente;
	protected String diagnostico;
	protected String teste_covid;
	protected String doenca_respiratoria;
	protected String batimento_cardiaco;
	protected String hipertensao;
	protected String oximetria;
	protected boolean radiometria_torax_normal;
	protected boolean tomografia_torax_normal;
	protected boolean ventilacao_mecanica;
	protected boolean diabetes;
	protected boolean obesidade;
	protected boolean ativo;
	protected int hospital_id;
	protected int hospital_destino_id;
	protected int paciente_id;
	
	// Nao necessariamente fazem sentido, mas aqui estao
	protected String cpf;
	protected String nome;
	protected String data_de_nascimento;
	protected String data_de_entrada;
	protected String nome_do_exame;
	protected String data_exame;
	protected String resultado_exame;
	protected String descricao_exame;
	
	public Prontuario(
			String data,
			String estado_do_paciente,
			String diagnostico,
			String teste_covid,
			String doenca_respiratoria,
			String batimento_cardiaco,
			String hipertensao,
			String oximetria,
			boolean radiometria_torax_normal,
			boolean tomografia_torax_normal,
			boolean ventilacao_mecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospital_id,
			int hospital_destino_id,
			int paciente_id
		) {
		super();
		
		this.data = data;
		this.estado_do_paciente = estado_do_paciente;
		this.diagnostico = diagnostico;
		this.teste_covid = teste_covid;
		this.doenca_respiratoria = doenca_respiratoria;
		this.batimento_cardiaco = batimento_cardiaco;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometria_torax_normal = radiometria_torax_normal;
		this.tomografia_torax_normal = tomografia_torax_normal;
		this.ventilacao_mecanica = ventilacao_mecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospital_id = hospital_id;
		this.hospital_destino_id = hospital_destino_id;
		this.paciente_id = paciente_id;
	}
	
	public Prontuario(
			int id,
			String data,
			String estado_do_paciente,
			String diagnostico,
			String teste_covid,
			String doenca_respiratoria,
			String batimento_cardiaco,
			String hipertensao,
			String oximetria,
			boolean radiometria_torax_normal,
			boolean tomografia_torax_normal,
			boolean ventilacao_mecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospital_id,
			int hospital_destino_id,
			int paciente_id
		) {
		super();
		
		this.id = id;
		this.data = data;
		this.estado_do_paciente = estado_do_paciente;
		this.diagnostico = diagnostico;
		this.teste_covid = teste_covid;
		this.doenca_respiratoria = doenca_respiratoria;
		this.batimento_cardiaco = batimento_cardiaco;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometria_torax_normal = radiometria_torax_normal;
		this.tomografia_torax_normal = tomografia_torax_normal;
		this.ventilacao_mecanica = ventilacao_mecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospital_id = hospital_id;
		this.hospital_destino_id = hospital_destino_id;
		this.paciente_id = paciente_id;
	}
	
	public Prontuario(String cpf, String nome, String data_de_nascimento, String data_de_entrada, String nome_exame,
			String descricao_exame, String data_exame, String resultado_exame) {
		// TODO Auto-generated constructor stub
		System.out.println("Construtor");
	}

	public Prontuario(int id, String cpf, String nome, String data_de_nascimento, String data_de_entrada,
			String nome_do_exame, String data_exame, String resultado_exame, String descricao_exame) {

		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.data_de_nascimento = data_de_nascimento;
		this.data_de_entrada = data_de_entrada;
		this.nome_do_exame = nome_do_exame;
		this.data_exame = data_exame;
		this.resultado_exame = resultado_exame;
		this.descricao_exame = descricao_exame;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return this.data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getEstadoDoPaciente() {
		return this.estado_do_paciente;
	}
	public void setEstadoDoPaciente(String estado_do_paciente) {
		this.estado_do_paciente = estado_do_paciente;
	}
	public String getDiagnostico() {
		return this.diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getTesteCovid() {
		return this.teste_covid;
	}
	public void setTesteCovid(String teste_covid) {
		this.teste_covid = teste_covid;
	}
	public String getDoencaRespiratoria() {
		return this.doenca_respiratoria;
	}
	public void setDoencaRespiratoria(String doenca_respiratoria) {
		this.doenca_respiratoria = doenca_respiratoria;
	}
	public String getBatimentoCardiaco() {
		return this.batimento_cardiaco;
	}
	public void setBatimentoCardiaco(String batimento_cardiaco) {
		this.batimento_cardiaco = batimento_cardiaco;
	}
	public String getHipertensao() {
		return this.hipertensao;
	}
	public void setHipertensao(String hipertensao) {
		this.hipertensao = hipertensao;
	}
	public String getOximetria() {
		return this.oximetria;
	}
	public void setOximetria(String oximetria) {
		this.oximetria = oximetria;
	}
	public boolean getRadiometriaToraxNormal() {
		return this.radiometria_torax_normal;
	}
	public void setRadiometriaToraxNormal(boolean radiometria_torax_normal) {
		this.radiometria_torax_normal = radiometria_torax_normal;
	}
	public boolean getTomografiaToraxNormal() {
		return this.tomografia_torax_normal;
	}
	public void setTomografiaToraxNormal(boolean tomografia_torax_normal) {
		this.tomografia_torax_normal = tomografia_torax_normal;
	}
	public boolean getVentilacaoMecanica() {
		return this.ventilacao_mecanica;
	}
	public void setVentilacaoMecanica(boolean ventilacao_mecanica) {
		this.ventilacao_mecanica = ventilacao_mecanica;
	}
	public boolean getDiabetes() {
		return this.diabetes;
	}
	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}
	public boolean getObesidade() {
		return this.diabetes;
	}
	public void setObesidade(boolean obesidade) {
		this.obesidade = obesidade;
	}
	public boolean getAtivo() {
		return this.ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getHospitalId() {
		return hospital_id;
	}
	public void setHospitalId(int hospital_id) {
		this.hospital_id = hospital_id;
	}
	public int getHospitalDestinoId() {
		return hospital_destino_id;
	}
	public void setHospitalDestinoId(int hospital_destino_id) {
		this.hospital_destino_id = hospital_destino_id;
	}
	public int getPacienteId() {
		return paciente_id;
	}
	public void setPacienteId(int paciente_id) {
		this.paciente_id = paciente_id;
	}

	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public String getData_de_nascimento() {
		return data_de_nascimento;
	}
	public String getData_de_entrada() {
		return data_de_entrada;
	}

	public String getNome_do_exame() {
		return nome_do_exame;
	}
	public String getData_exame() {
		return data_exame;
	}
	public String getResultado_exame() {
		return resultado_exame;
	}
	public String getDescricao_exame() {
		return resultado_exame;
	}
	
	public void setCpf() {
	}
	public void setNome() {
	}
	public void setData_de_nacimento() {
	}
	public void setData_de_entrada() {
	}
	public void setDome_do_exame() {
	}
	public void setData_exame() {
	}
	public void setResultado_exame() {
	}
	public void setDescricao_exame() {
	}
}
