package net.javaguides.usermanagement.model;

/**
 * Paciente.java
 * This is a model class represents a Paciente entity
 * @author Hugo Martins
 *
 */

public class Paciente {
	protected int id;
	protected String cpf;
	protected String nome;
	protected String prontuario;
	protected String dataDeEntrada;
	
	public Paciente() {
	}
	
	public Paciente(String cpf, String nome, String prontuario, String dataDeEntrada) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.prontuario = prontuario;
		this.dataDeEntrada = dataDeEntrada;
	}
	
	public Paciente(int id, String cpf, String nome, String prontuario, String dataDeEntrada) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.prontuario = prontuario;
		this.dataDeEntrada = dataDeEntrada;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProntuario() {
		return prontuario;
	}
	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}
	public String getDataDeEntrada() {
		return dataDeEntrada;
	}
	public void setDataDeEntrada(String dataDeEntrada) {
		this.dataDeEntrada = dataDeEntrada;
	}
}
