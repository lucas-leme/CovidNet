package net.javaguides.usermanagement.model;

/**
 * Exames.java
 * This is a model class represents a Exames entity
 * @author Hugo Martins
 *
 */

public class FilaDePaciente {
	protected int id;
	protected String data;
	protected int ordem;
	protected int prioridade;
	protected int paciente_id;
	protected String nome;
	
	public FilaDePaciente(int id) {
		super();

		this.id = id;
	}
	
	public FilaDePaciente(String data, int ordem, int prioridade, int paciente_id) {
		super();

		this.data = data;
		this.ordem = ordem;
		this.prioridade = prioridade;
		this.paciente_id = paciente_id;
	}
	
	public FilaDePaciente(int id, String data, int ordem, int prioridade, int paciente_id) {
		super();

		this.id = id;
		this.data = data;
		this.ordem = ordem;
		this.prioridade = prioridade;
		this.paciente_id = paciente_id;
	}
	
	
	public FilaDePaciente(int id, String data, int ordem, int prioridade, String nome) {
		super();

		this.id = id;
		this.data = data;
		this.ordem = ordem;
		this.prioridade = prioridade;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public int getPacienteId() {
		return paciente_id;
	}
	public void setPacienteId(int paciente_id) {
		this.paciente_id = paciente_id;
	}
	
	public String getNome() { return this.nome; }
	public void setNome(String nome) { this.nome = nome; }
}
