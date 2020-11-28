package net.javaguides.usermanagement.model;

public class Municipio {

	protected int id;
	protected String nome;
	
	public Municipio(String nome) {
		super();
		
		this.nome = nome;
	}
	
	public Municipio(int id, String nome) {
		super();
		
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
