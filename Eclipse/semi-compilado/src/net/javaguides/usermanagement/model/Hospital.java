package net.javaguides.usermanagement.model;

public class Hospital {

	protected int id;
	protected String nome;
	protected String telefone;
	protected String endereco;
	protected String estado;
	protected int municipio_id;
	protected int leitos_disponiveis;
	
	public Hospital(
			String nome,
			String telefone,
			String endereco,
			String estado,
			int municipio_id
		) {
		super();
		
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.estado = estado;
		this.municipio_id = municipio_id;
	}
	
	public Hospital(
			int id,
			String nome,
			String telefone,
			String endereco,
			String estado,
			int municipio_id
		) {
		super();
		
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.estado = estado;
		this.municipio_id = municipio_id;
	}
	
	public Hospital(
			String nome,
			int leitos_disponiveis
		) {
		super();
		
		this.nome = nome;
		this.leitos_disponiveis = leitos_disponiveis;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getMunicipioId() {
		return municipio_id;
	}
	public void setMunicipioId(int municipio_id) {
		this.municipio_id = municipio_id;
	}
	public int getLeitosDisponiveis() {
		return leitos_disponiveis;
	}
	public void setLeitosDisponiveis(int leitos_disponiveis) {
		this.leitos_disponiveis = leitos_disponiveis;
	}
}
