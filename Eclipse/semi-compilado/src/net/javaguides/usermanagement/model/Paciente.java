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
	protected String dataDeNascimento;
	protected String endereco;
	
	public Paciente(String cpf, String nome, String data_de_nascimento, String endereco) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataDeNascimento = data_de_nascimento;
		this.endereco = endereco;
	}
	
	public Paciente(int id, String cpf, String nome, String data_de_nascimento, String endereco) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataDeNascimento = data_de_nascimento;
		this.endereco = endereco;
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
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String data_de_nascimento) {
		this.dataDeNascimento = data_de_nascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
