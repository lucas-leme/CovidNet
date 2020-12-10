package net.javaguides.usermanagement.model;

/**
 * Paciente.java
 * This is a model class represents a Paciente entity
 * @author Hugo Martins
 *
 */

public class Funcionario {
	protected int id;
	protected String nome;
	protected String especializacao;
	protected String cargo;
	protected String login;
	protected String senha;
	
	public Funcionario(String nome, String especializacao, String cargo, String login, String senha) {
		super();
		
		this.nome = nome;
		this.especializacao = especializacao;
		this.cargo = cargo;
		this.login = login;
		this.senha = senha;
	}
	
	public Funcionario(int id, String nome, String especializacao, String cargo, String login, String senha) {
		super();
		
		this.id = id;
		this.nome = nome;
		this.especializacao = especializacao;
		this.cargo = cargo;
		this.login = login;
		this.senha = senha;
	}
	
	public Funcionario(String cargo) {
		super();
		this.cargo = cargo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEspecializacao() {
		return especializacao;
	}
	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
