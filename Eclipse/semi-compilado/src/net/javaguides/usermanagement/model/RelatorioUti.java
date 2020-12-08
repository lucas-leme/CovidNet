package net.javaguides.usermanagement.model;

/**
 * Exames.java
 * This is a model class represents a RelatorioVagas entity
 * @author Hugo Martins
 *
 */

public class RelatorioUti {
	protected String data_pedido;
	protected int pedidos;
	protected String data_alocacao;
	protected int alocacoes;
	
	public RelatorioUti(String data_pedido, int pedidos) {
		super();

		this.data_pedido = data_pedido;
		this.pedidos = pedidos;
	}
	
	public RelatorioUti(int alocacoes, String data_alocacao) {
		super();

		this.data_alocacao = data_alocacao;
		this.alocacoes = alocacoes;
	}
	
	public int getPedidos() {
		return pedidos;
	}
	public void setPedidos(int pedidos) {
		this.pedidos = pedidos;
	}
	public String getDataPedido() {
		return data_pedido;
	}
	public void setDataPedido(String data_pedido) {
		this.data_pedido = data_pedido;
	}
	public int getAlocacoes() {
		return alocacoes;
	}
	public void setAlocacoes(int alocacoes) {
		this.alocacoes = alocacoes;
	}
	public String getDataAlocacao() {
		return data_alocacao;
	}
	public void setDataAlocacao(String data_alocacao) {
		this.data_alocacao = data_alocacao;
	}
	
}
