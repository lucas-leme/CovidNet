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
	protected String estadoDoPaciente;
	protected String diagnostico;
	protected String testeCovid;
	protected boolean doencaRespiratoria;
	protected boolean batimentoCardiacoNormal;
	protected boolean hipertensao;
	protected int oximetria;
	protected boolean radiometriaToraxNormal;
	protected boolean tomografiaToraxNormal;
	protected boolean ventilacaoMecanica;
	protected boolean diabetes;
	protected boolean obesidade;
	protected boolean ativo;
	protected int hospitalId;
	protected int hospitalDestinoId;

	protected int pacienteId;
	
	public Prontuario(
			String data,
			String estadoDoPaciente,
			String diagnostico,
			String testeCovid,
			boolean doencaRespiratoria,
			boolean batimentoCardiacoNormal,
			boolean hipertensao,
			int oximetria,
			boolean radiometriaToraxNormal,
			boolean tomografiaToraxNormal,
			boolean ventilacaoMecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospitalId,
			int hospitalDestinoId,
			int pacienteId
		) {
		super();
		
		this.data = data;
		this.estadoDoPaciente = estadoDoPaciente;
		this.diagnostico = diagnostico;
		this.testeCovid = testeCovid;
		this.doencaRespiratoria = doencaRespiratoria;
		this.batimentoCardiacoNormal = batimentoCardiacoNormal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometriaToraxNormal = radiometriaToraxNormal;
		this.tomografiaToraxNormal = tomografiaToraxNormal;
		this.ventilacaoMecanica = ventilacaoMecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospitalId = hospitalId;
		this.hospitalDestinoId = hospitalDestinoId;
		this.pacienteId = pacienteId;
	}
	
	public Prontuario(
			String data,
			String estadoDoPaciente,
			String diagnostico,
			String TesteCovid,
			boolean doencaRespiratoria,
			boolean batimentoCardiacoNormal,
			boolean hipertensao,
			int oximetria,
			boolean radiometriaToraxNormal,
			boolean tomografiaToraxNormal,
			boolean ventilacaoMecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospitalId
		) {
		super();
		
		this.data = data;
		this.estadoDoPaciente = estadoDoPaciente;
		this.diagnostico = diagnostico;
		this.testeCovid = TesteCovid;
		this.doencaRespiratoria = doencaRespiratoria;
		this.batimentoCardiacoNormal = batimentoCardiacoNormal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometriaToraxNormal = radiometriaToraxNormal;
		this.tomografiaToraxNormal = tomografiaToraxNormal;
		this.ventilacaoMecanica = ventilacaoMecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospitalId = hospitalId;
	}
	
	public Prontuario(
			String data,
			String estadoDoPaciente,
			String diagnostico,
			String TesteCovid,
			boolean doencaRespiratoria,
			boolean batimentoCardiacoNormal,
			boolean hipertensao,
			int oximetria,
			boolean radiometriaToraxNormal,
			boolean tomografiaToraxNormal,
			boolean ventilacaoMecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospitalId,
			int pacienteId
		) {
		super();
		
		this.data = data;
		this.estadoDoPaciente = estadoDoPaciente;
		this.diagnostico = diagnostico;
		this.testeCovid = TesteCovid;
		this.doencaRespiratoria = doencaRespiratoria;
		this.batimentoCardiacoNormal = batimentoCardiacoNormal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometriaToraxNormal = radiometriaToraxNormal;
		this.tomografiaToraxNormal = tomografiaToraxNormal;
		this.ventilacaoMecanica = ventilacaoMecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospitalId = hospitalId;
		this.pacienteId = pacienteId;
	}
	
	public Prontuario(
			int id,
			String data,
			String estadoDoPaciente,
			String diagnostico,
			String TesteCovid,
			boolean doencaRespiratoria,
			boolean batimentoCardiacoNormal,
			boolean hipertensao,
			int oximetria,
			boolean radiometriaToraxNormal,
			boolean tomografiaToraxNormal,
			boolean ventilacaoMecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospitalId,
			int hospitalDestinoId,
			int pacienteId
		) {
		super();
		
		this.id = id;
		this.data = data;
		this.estadoDoPaciente = estadoDoPaciente;
		this.diagnostico = diagnostico;
		this.testeCovid = TesteCovid;
		this.doencaRespiratoria = doencaRespiratoria;
		this.batimentoCardiacoNormal = batimentoCardiacoNormal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometriaToraxNormal = radiometriaToraxNormal;
		this.tomografiaToraxNormal = tomografiaToraxNormal;
		this.ventilacaoMecanica = ventilacaoMecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospitalId = hospitalId;
		this.hospitalDestinoId = hospitalDestinoId;
		this.pacienteId = pacienteId;
	}
	
	public Prontuario(
			int id,
			String data,
			String estadoDoPaciente,
			String diagnostico,
			String testeCovid,
			boolean doencaRespiratoria,
			boolean batimentoCardiacoNormal,
			boolean hipertensao,
			int oximetria,
			boolean radiometriaToraxNormal,
			boolean tomografiaToraxNormal,
			boolean ventilacaoMecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospitalId,
			int hospitalDestinoId
		) {
		super();
		
		this.id = id;
		this.data = data;
		this.estadoDoPaciente = estadoDoPaciente;
		this.diagnostico = diagnostico;
		this.testeCovid = testeCovid;
		this.doencaRespiratoria = doencaRespiratoria;
		this.batimentoCardiacoNormal = batimentoCardiacoNormal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometriaToraxNormal = radiometriaToraxNormal;
		this.tomografiaToraxNormal = tomografiaToraxNormal;
		this.ventilacaoMecanica = ventilacaoMecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospitalId = hospitalId;
		this.hospitalDestinoId = hospitalDestinoId;
	}
	
	public Prontuario(
			String estadoDoPaciente,
			String diagnostico,
			String testeCovid,
			boolean doencaRespiratoria,
			boolean batimentoCardiacoNormal,
			boolean hipertensao,
			int oximetria,
			boolean radiometriaToraxNormal,
			boolean tomografiaToraxNormal,
			boolean ventilacaoMecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospitalId,
			int pacienteId
		) {
		super();
		
		this.estadoDoPaciente = estadoDoPaciente;
		this.diagnostico = diagnostico;
		this.testeCovid = testeCovid;
		this.doencaRespiratoria = doencaRespiratoria;
		this.batimentoCardiacoNormal = batimentoCardiacoNormal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometriaToraxNormal = radiometriaToraxNormal;
		this.tomografiaToraxNormal = tomografiaToraxNormal;
		this.ventilacaoMecanica = ventilacaoMecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospitalId = hospitalId;
		this.pacienteId = pacienteId;
	}
	
	public Prontuario(
			int id,
			String data,
			String estadoDoPaciente,
			String diagnostico,
			String testeCovid,
			boolean doencaRespiratoria,
			boolean batimentoCardiacoNormal,
			boolean hipertensao,
			int oximetria,
			boolean radiometriaToraxNormal,
			boolean tomografiaToraxNormal,
			boolean ventilacaoMecanica,
			boolean diabetes,
			boolean obesidade,
			boolean ativo,
			int hospitalId
		) {
		super();
		
		this.id = id;
		this.data = data;
		this.estadoDoPaciente = estadoDoPaciente;
		this.diagnostico = diagnostico;
		this.testeCovid = testeCovid;
		this.doencaRespiratoria = doencaRespiratoria;
		this.batimentoCardiacoNormal = batimentoCardiacoNormal;
		this.hipertensao = hipertensao;
		this.oximetria = oximetria;
		this.radiometriaToraxNormal = radiometriaToraxNormal;
		this.tomografiaToraxNormal = tomografiaToraxNormal;
		this.ventilacaoMecanica = ventilacaoMecanica;
		this.diabetes = diabetes;
		this.obesidade = obesidade;
		this.ativo = ativo;
		this.hospitalId = hospitalId;
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
		return this.estadoDoPaciente;
	}
	public void setEstadoDoPaciente(String estadoDoPaciente) {
		this.estadoDoPaciente = estadoDoPaciente;
	}
	public String getDiagnostico() {
		return this.diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getTesteCovid() {
		return this.testeCovid;
	}
	public void setTesteCovid(String TesteCovid) {
		this.testeCovid = TesteCovid;
	}
	public boolean getDoencaRespiratoria() {
		return this.doencaRespiratoria;
	}
	public void setDoencaRespiratoria(boolean doencaRespiratoria) {
		this.doencaRespiratoria = doencaRespiratoria;
	}
	public boolean getBatimentoCardiacoNormal() {
		return this.batimentoCardiacoNormal;
	}
	public void getBatimentoCardiacoNormal(boolean batimentoCardiacoNormal) {
		this.batimentoCardiacoNormal = batimentoCardiacoNormal;
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
		return this.radiometriaToraxNormal;
	}
	public void setRadiometriaToraxNormal(boolean radiometriaToraxNormal) {
		this.radiometriaToraxNormal = radiometriaToraxNormal;
	}
	public boolean getTomografiaToraxNormal() {
		return this.tomografiaToraxNormal;
	}
	public void setTomografiaToraxNormal(boolean tomografiaToraxNormal) {
		this.tomografiaToraxNormal = tomografiaToraxNormal;
	}
	public boolean getVentilacaoMecanica() {
		return this.ventilacaoMecanica;
	}
	public void setVentilacaoMecanica(boolean ventilacaoMecanica) {
		this.ventilacaoMecanica = ventilacaoMecanica;
	}
	public boolean getDiabetes() {
		return this.diabetes;
	}
	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}
	public boolean getObesidade() {
		return this.obesidade;
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
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	public int getHospitalDestinoId() {
		return hospitalDestinoId;
	}
	public void setHospitalDestinoId(int hospitalDestinoId) {
		this.hospitalDestinoId = hospitalDestinoId;
	}
	public int getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(int pacienteId) {
		this.pacienteId = pacienteId;
	}
}
