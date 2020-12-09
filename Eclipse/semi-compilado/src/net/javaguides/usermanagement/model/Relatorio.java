package net.javaguides.usermanagement.model;
import java.util.Date;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Relatorio {

	protected String tipo;
	protected Date data;
	protected int id;
	
	public Relatorio(String tipo, Date data, int id) {
		super();

		this.id = id;
		this.tipo = tipo;
		this.data = data;
	}
	
	public Relatorio(String tipo, Date data) {
		super();

		this.tipo = tipo;
		this.data = data;
	}

	public void setId (int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}


