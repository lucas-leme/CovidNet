package net.javaguides.usermanagement.model;

/**
 * Exames.java
 * This is a model class represents a RelatorioVagas entity
 * @author Hugo Martins
 *
 */

public class RelatorioUti {
	protected String dataPedido;
	protected int pedidos;
	protected String dataAlocacao;
	protected int alocacoes;
	
	public RelatorioUti(String dataPedido, int pedidos) {
		super();

		this.dataPedido = dataPedido;
		this.pedidos = pedidos;
	}
	
	public RelatorioUti(int alocacoes, String dataAlocacao) {
		super();

		this.dataAlocacao = dataAlocacao;
		this.alocacoes = alocacoes;
	}
	
	public int getPedidos() {
		return pedidos;
	}
	public void setPedidos(int pedidos) {
		this.pedidos = pedidos;
	}
	public String getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	public int getAlocacoes() {
		return alocacoes;
	}
	public void setAlocacoes(int alocacoes) {
		this.alocacoes = alocacoes;
	}
	public String getDataAlocacao() {
		return dataAlocacao;
	}
	public void setDataAlocacao(String dataAlocacao) {
		this.dataAlocacao = dataAlocacao;
	}
	
}
