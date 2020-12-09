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
	protected String dataInicial, dataFinal;

	public RelatorioHospitalar(String nomeHospital) {
		super("Hospitalar", new Date(), 0);
		
		this.tipo = "Hospitalar";

		this.nomeHospital = nomeHospital;
	}

	public RelatorioHospitalar(String dataInicio, String dataFim) 
	{
		super("Hospitalar", new Date());
		
		this.dataInicial = dataInicio;
		this.dataFinal = dataFim;
	}


	public void setNomeHospital (String nomeHospital) {
		this.nomeHospital = nomeHospital;
	}
	
	public String getNomeHospital() {
		return nomeHospital;
	}
	
	public String getDataInicial() { return this.dataInicial; }
	public void setDataInicial(String dataInicial) { this.dataInicial = dataInicial; }
	
	public String getDataFinal() { return this.dataFinal; }
	public void setDataFinal(String dataFinal) { this.dataFinal = dataFinal; }
}


