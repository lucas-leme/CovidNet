package net.javaguides.usermanagement.model;

import java.util.List;

public class Hospital {

	private List<Leito> listLeitos;
	private String nome;
	
	public Hospital(String nome)
	{
		this.nome = nome;
	}
	
	public List<Leito> listarLeitos()
	{
		return null;
	}
	
	boolean reservarVaga()
	{
		return false;
	}
	
	void escolherFiltros()
	{
		
	}
}
