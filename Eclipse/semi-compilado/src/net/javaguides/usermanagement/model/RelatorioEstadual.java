package net.javaguides.usermanagement.model;
import java.util.Date;
/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class RelatorioEstadual extends Relatorio {

	protected String nomeEstado;
	protected String numeroMunicipios;
	protected String numeroHospitais;
	
	public RelatorioEstadual(String nomeEstado, String numeroMunicipios, String numeroHospitais) {
		super("Estadual", new Date(), 0);

		this.nomeEstado = nomeEstado;
		this.numeroMunicipios = numeroMunicipios;
		this.numeroHospitais = numeroHospitais;
	}

	
	public void setNomeEstado (String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	
	public String getNomeEstado() {
		return nomeEstado;
	}
	
	public void setNumeroMunicipios (String numeroMunicipios) {
		this.numeroMunicipios = numeroMunicipios;
	}
	
	public String getNumeroMunicipios() {
		return numeroMunicipios;
	}
	
	public void setNumeroHospitais (String numeroHospitais) {
		this.numeroHospitais = numeroHospitais;
	}
	
	public String getNumeroHospitais() {
		return numeroHospitais;
	}
}


