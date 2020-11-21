package net.javaguides.usermanagement.model;

/**
 * Prontuario.java
 * This is a model class represents a Prontuario entity
 * @author Hugo Martins
 *
 */

public class Prontuario {
	protected int id;
	protected String cpf;
	protected String nome;
	protected String data_de_nascimento;
	protected String data_de_entrada;
	protected String nome_do_exame;
	protected String descricao_exame;
	protected String data_exame;
	protected String resultado_exame;
	
	public Prontuario() {
	}
	
	public Prontuario(String cpf, String nome, String data_de_entrada, String data_de_nascimento, String nome_do_exame, String descricao_exame, String data_exame, String resultado_exame) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.data_de_nascimento = data_de_nascimento;
		this.data_de_entrada = data_de_entrada;
		this.nome_do_exame = nome_do_exame;
		this.descricao_exame = descricao_exame;
		this.data_exame = data_exame;
		this.resultado_exame = resultado_exame;
	}
	
	public Prontuario(int id, String cpf, String nome, String data_de_nascimento, String data_de_entrada, String nome_do_exame, String descricao_exame, String data_exame, String resultado_exame) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.data_de_nascimento = data_de_nascimento;
		this.data_de_entrada = data_de_entrada;
		this.nome_do_exame = nome_do_exame;
		this.descricao_exame = descricao_exame;
		this.data_exame = data_exame;
		this.resultado_exame = resultado_exame;
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
	public String getDataDeEntrada() {
		return data_de_entrada;
	}
	public void setDataDeEntrada(String data_de_entrada) {
		this.data_de_entrada = data_de_entrada;
	}
	public String getDataDeNascimento() {
		return data_de_nascimento;
	}
	public void setDataDeNascimento(String data_de_nascimento) {
		this.data_de_nascimento = data_de_nascimento;
	}
	public String getNomeDoExame() {
		return nome_do_exame;
	}
	public void setNomeDoExame(String nome_do_exame) {
		this.nome_do_exame = nome_do_exame;
	}
	public String getDescricaoExame() {
		return descricao_exame;
	}
	public void setDescricaoExame(String descricao_exame) {
		this.descricao_exame = descricao_exame;
	}
	public String getDataExame() {
		return data_exame;
	}
	public void setDataExame(String data_exame) {
		this.data_exame = data_exame;
	}
	public String getResultadoExame() {
		return resultado_exame;
	}
	public void setResultadoExame(String resultado_exame) {
		this.resultado_exame = resultado_exame;
	}
}
