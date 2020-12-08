package net.javaguides.usermanagement.model;

/**
 * Exames.java
 * This is a model class represents a RelatorioVagas entity
 * @author Hugo Martins
 *
 */

public class RelatorioVagas {
	protected int id;
	protected String data;
	protected int vagas_ocupadas;
	protected int vagas_totais;
	protected int hospital_id;
	
	public RelatorioVagas(String data, int vagas_ocupadas, int vagas_totais, int hospital_id) {
		super();

		this.data = data;
		this.vagas_ocupadas = vagas_ocupadas;
		this.vagas_totais = vagas_totais;
		this.hospital_id = hospital_id;
	}
	
	public RelatorioVagas(int id, String data, int vagas_ocupadas, int vagas_totais, int hospital_id) {
		super();

		this.id = id;
		this.data = data;
		this.vagas_ocupadas = vagas_ocupadas;
		this.vagas_totais = vagas_totais;
		this.hospital_id = hospital_id;
	}
	
	public RelatorioVagas(String data, int vagas_ocupadas, int vagas_totais) {
		super();

		this.data = data;
		this.vagas_ocupadas = vagas_ocupadas;
		this.vagas_totais = vagas_totais;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getVagasOcupadas() {
		return vagas_ocupadas;
	}
	public void setVagasOcupadas(int vagas_ocupadas) {
		this.vagas_ocupadas = vagas_ocupadas;
	}
	public int getVagasTotais() {
		return vagas_totais;
	}
	public void setVagasTotais(int vagas_totais) {
		this.vagas_totais = vagas_totais;
	}
	public int getHospitalId() {
		return hospital_id;
	}
	public void setHospitalId(int hospital_id) {
		this.hospital_id = hospital_id;
	}
}
