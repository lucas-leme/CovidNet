package net.javaguides.usermanagement.model;

public class Medico {

	private String CRM;
	private String nome;
	private String senha;
	private String especialidade;
	private	String turno;
	
	public Medico(String CRM, String nome, String senha,
			String especialidade, String turno)
	{
		this.CRM = CRM;
		this.nome = nome;
		this.senha = senha;
		this.especialidade = especialidade;
		this.turno = turno;
	}
	
	public String getCRM() { return this.CRM; }	
	public String getNome() { return this.nome; }	
	public String getSenha() { return this.senha; }	
	public String getEspecialidade() { return this.especialidade; }
	public String getTurno() { return this.turno; }
}
