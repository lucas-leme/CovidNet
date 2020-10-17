package net.javaguides.usermanagement.model;
import java.util.Date;
/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class RelatorioMunicipal extends Relatorio {

	protected String nomeMunicipio;
	protected int numeroHospitais;
	
	public RelatorioMunicipal(String nomeMunicipio, int numeroHospitais) {
		super("Municipal", new Date(), 0);

		this.nomeMunicipio = nomeMunicipio;
		this.numeroHospitais = numeroHospitais;
	}

	
	public void setNomeMunicipio (String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	
	
	public void setNumeroHospitais (int numeroHospitais) {
		this.numeroHospitais = numeroHospitais;
	}
	
	public int getNumeroHospitais() {
		return numeroHospitais;
	}
}


