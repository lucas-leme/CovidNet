package net.javaguides.usermanagement.model;

public class Leito {
	
	protected int id;
	protected boolean ocupado;
	protected String medico;
	protected String paciente;
	protected String enfermeiro;
	protected String hospital;
	protected int hospitalId;
	protected int pacienteId;
	
	public Leito(int id, boolean ocupado, int pacienteId, int hospitalId) {
		super();
		
		this.id = id;
		this.hospitalId = hospitalId;
		this.pacienteId = pacienteId;
		this.ocupado = ocupado;
	}
	
	public Leito(String medico, String paciente, String enfermeiro) {
		super();
		
		this.medico = medico;
		this.paciente = paciente;
		this.enfermeiro = enfermeiro;
		this.ocupado = true;
	}
	
	public Leito(int id, boolean ocupado, String paciente, String hospital ) {
		super();
		
		this.id = id;
		this.ocupado = ocupado;
		this.paciente = paciente;
		this.hospital = hospital;	
	}
	
	public Leito(int id, String medico, String paciente, String enfermeiro) {
		super();
		
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
		this.enfermeiro = enfermeiro;
		this.ocupado = true;
	}

	public int getId() { return this.id; }
	public int getHospitalId() { return this.hospitalId; }
	public int getPacienteId() { return this.pacienteId; }
	public String getMedico() { return this.medico; }
	public String getPaciente() { return this.paciente; }
	public String getEnfermeiro() { return this.enfermeiro; }
	public String getHospital() { return this.hospital; }
	
	
	public void setHospitalId(int hospitalId) { this.hospitalId = hospitalId; }
	public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }
	public void setMedico(String newName) { this.medico = newName; }
	public void setPaciente(String newName) { this.paciente = newName; }
	public void setEnfermeiro(String newName) { this.enfermeiro = newName; }
	public void setId(int newId) { this.id = newId; }
}