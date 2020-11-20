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
	protected int numeroMunicipios;
	protected int numeroHospitais;
	
	public RelatorioEstadual(int id, String nomeEstado, int numeroMunicipios, int numeroHospitais) {
		super("Estadual", new Date(), id);

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
	
	public void setNumeroMunicipios (int numeroMunicipios) {
		this.numeroMunicipios = numeroMunicipios;
	}
	
	public int getNumeroMunicipios() {
		return numeroMunicipios;
	}
	
	public void setNumeroHospitais (int numeroHospitais) {
		this.numeroHospitais = numeroHospitais;
	}
	
	public int getNumeroHospitais() {
		return numeroHospitais;
	}
}


