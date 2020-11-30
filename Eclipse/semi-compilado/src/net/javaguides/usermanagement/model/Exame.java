package net.javaguides.usermanagement.model;

/**
 * Exames.java
 * This is a model class represents a Exames entity
 * @author Hugo Martins
 *
 */

public class Exame {
	protected int id;
	protected String nome;
	protected String data;
	protected String descricao;
	protected String resultado;
	protected int prontuario_id;
	
	public Exame(String nome, String data, String descricao, String resultado, int prontuario_id) {
		super();

		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.resultado = resultado;
		this.prontuario_id = prontuario_id;
	}
	
	public Exame(int id, String nome, String data, String descricao, String resultado, int prontuario_id) {
		super();
		
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.resultado = resultado;
		this.prontuario_id = prontuario_id;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public int getProntuarioId() {
		return id;
	}
	public void setProntuarioId(int prontuario_id) {
		this.prontuario_id = prontuario_id;
	}
}
