package net.javaguides.usermanagement.model;
import java.util.Date;
/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class RelatorioHospitalar extends Relatorio {

	protected String nomeHospital;

	public RelatorioHospitalar(String nomeHospital) {
		super("Hospitalar", new Date(), 0);
		
		this.tipo = "Hospitalar";

		this.nomeHospital = nomeHospital;
	}

	
	public void setNomeHospital (String nomeHospital) {
		this.nomeHospital = nomeHospital;
	}
	
	public String getNomeHospital() {
		return nomeHospital;
	}
}


