package net.javaguides.usermanagement.model;

public class Prontuario {

	private int num_prontuario;
	private Paciente paciente;
	
	public Prontuario(Paciente paciente)
	{
		this.paciente = paciente;
	}
	
	void gerarFormulario() {}
	void getProntuario() {}
	void setProntuario() {}
	
}
