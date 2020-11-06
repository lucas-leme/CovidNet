package net.javaguides.usermanagement.model;

public class Leito {
	
	protected int id;
	protected boolean ocupado;
	protected String medico;
	protected String paciente;
	protected String enfermeiro;
	
	public Leito() {
	}
	
	public Leito(String medico, String paciente, String enfermeiro) {
		super();
		
		this.medico = medico;
		this.paciente = paciente;
		this.enfermeiro = enfermeiro;
		this.ocupado = true;
	}
	
	public Leito(int id, String medico, String paciente, String enfermeiro) {
		super();
		
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
		this.enfermeiro = enfermeiro;
		this.ocupado = true;
	}

	/*public Leito(int id, String name, String email, String country) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
	}*/

	public int getId() { return this.id; }
	public String getMedico() { return this.medico; }
	public String getPaciente() { return this.paciente; }
	public String getEnfermeiro() { return this.enfermeiro; }
	
	public void setMedico(String newName) { this.medico = newName; }
	public void setPaciente(String newName) { this.paciente = newName; }
	public void setEnfermeiro(String newName) { this.enfermeiro = newName; }
	public void setId(int newId) { this.id = newId; }
}