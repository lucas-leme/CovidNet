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
	protected boolean doenca_respiratoria;
	protected boolean batimento_cardiaco_normal;
	protected boolean hipertensao;
	protected int oximetria;
	protected boolean radiometria_torax_normal;
	protected boolean tomografia_torax_normal;
	protected boolean ventilacao_mecanica;
	protected boolean diabetes;
	protected boolean obesidade;
	protected boolean ativo;
	protected int hospital_id;
	protected int hospital_destino_id;
	protected int paciente_id;
	
	public Prontuario(
			String data,
			String estado_do_paciente,
			String diagnostico,
			String teste_covid,
			boolean doenca_respiratoria,
			boolean batimento_cardiaco_normal,
			boolean hipertensao,
			int oximetria,
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
		this.batimento_cardiaco_normal = batimento_cardiaco_normal;
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
			String data,
			String estado_do_paciente,
			String diagnostico,
			String teste_covid,
			boolean doenca_respiratoria,
			boolean batimento_cardiaco_normal,
			boolean hipertensao,
			int oximetria,
			boolean radiometria_torax_normal,
			boolean tomografia_torax_normal,
			boolean ventilacao_mecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospital_id
		) {
		super();
		
		this.data = data;
		this.estado_do_paciente = estado_do_paciente;
		this.diagnostico = diagnostico;
		this.teste_covid = teste_covid;
		this.doenca_respiratoria = doenca_respiratoria;
		this.batimento_cardiaco_normal = batimento_cardiaco_normal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometria_torax_normal = radiometria_torax_normal;
		this.tomografia_torax_normal = tomografia_torax_normal;
		this.ventilacao_mecanica = ventilacao_mecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospital_id = hospital_id;
	}
	
	public Prontuario(
			String data,
			String estado_do_paciente,
			String diagnostico,
			String teste_covid,
			boolean doenca_respiratoria,
			boolean batimento_cardiaco_normal,
			boolean hipertensao,
			int oximetria,
			boolean radiometria_torax_normal,
			boolean tomografia_torax_normal,
			boolean ventilacao_mecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospital_id,
			int paciente_id
		) {
		super();
		
		this.data = data;
		this.estado_do_paciente = estado_do_paciente;
		this.diagnostico = diagnostico;
		this.teste_covid = teste_covid;
		this.doenca_respiratoria = doenca_respiratoria;
		this.batimento_cardiaco_normal = batimento_cardiaco_normal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometria_torax_normal = radiometria_torax_normal;
		this.tomografia_torax_normal = tomografia_torax_normal;
		this.ventilacao_mecanica = ventilacao_mecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospital_id = hospital_id;
		this.paciente_id = paciente_id;
	}
	
	public Prontuario(
			int id,
			String data,
			String estado_do_paciente,
			String diagnostico,
			String teste_covid,
			boolean doenca_respiratoria,
			boolean batimento_cardiaco_normal,
			boolean hipertensao,
			int oximetria,
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
		this.batimento_cardiaco_normal = batimento_cardiaco_normal;
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
			boolean doenca_respiratoria,
			boolean batimento_cardiaco_normal,
			boolean hipertensao,
			int oximetria,
			boolean radiometria_torax_normal,
			boolean tomografia_torax_normal,
			boolean ventilacao_mecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospital_id,
			int hospital_destino_id
		) {
		super();
		
		this.id = id;
		this.data = data;
		this.estado_do_paciente = estado_do_paciente;
		this.diagnostico = diagnostico;
		this.teste_covid = teste_covid;
		this.doenca_respiratoria = doenca_respiratoria;
		this.batimento_cardiaco_normal = batimento_cardiaco_normal;
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
	}
	
	public Prontuario(
			String estado_do_paciente,
			String diagnostico,
			String teste_covid,
			boolean doenca_respiratoria,
			boolean batimento_cardiaco_normal,
			boolean hipertensao,
			int oximetria,
			boolean radiometria_torax_normal,
			boolean tomografia_torax_normal,
			boolean ventilacao_mecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospital_id,
			int paciente_id
		) {
		super();
		
		this.estado_do_paciente = estado_do_paciente;
		this.diagnostico = diagnostico;
		this.teste_covid = teste_covid;
		this.doenca_respiratoria = doenca_respiratoria;
		this.batimento_cardiaco_normal = batimento_cardiaco_normal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometria_torax_normal = radiometria_torax_normal;
		this.tomografia_torax_normal = tomografia_torax_normal;
		this.ventilacao_mecanica = ventilacao_mecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospital_id = hospital_id;
		this.paciente_id = paciente_id;
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
	public boolean getDoencaRespiratoria() {
		return this.doenca_respiratoria;
	}
	public void setDoencaRespiratoria(boolean doenca_respiratoria) {
		this.doenca_respiratoria = doenca_respiratoria;
	}
	public boolean getBatimentoCardiacoNormal() {
		return this.batimento_cardiaco_normal;
	}
	public void getBatimentoCardiacoNormal(boolean batimento_cardiaco_normal) {
		this.batimento_cardiaco_normal = batimento_cardiaco_normal;
	}
	public boolean getHipertensao() {
		return this.hipertensao;
	}
	public void setHipertensao(boolean hipertensao) {
		this.hipertensao = hipertensao;
	}
	public int getOximetria() {
		return this.oximetria;
	}
	public void setOximetria(int oximetria) {
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
}
