package net.javaguides.usermanagement.model;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

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
	
	protected String dataInicio, dataFim;
	
	public Relatorio(String tipo, Date data, int id) {
		super();

		this.id = id;
		this.tipo = tipo;
		this.data = data;
	}
	
	public Relatorio(String dataInicio, String dataFim) {
		super();

		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		
		data = new Date();
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

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
}


