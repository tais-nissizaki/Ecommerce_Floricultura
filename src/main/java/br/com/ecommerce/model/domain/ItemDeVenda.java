package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="itemDeVenda")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class ItemDeVenda extends EntidadeDominio {
	
	@Column(name="quantidade")
	private int quantidade;
	
	@Column(name="valorDeVenda")
	private double valorDeVenda;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idProduto")
	private Produto produto;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idVenda")
	private Venda venda;
	
	public ItemDeVenda() {
		
	}
	
	public ItemDeVenda(int quantidade, double valorDeVenda) {
		this.quantidade = quantidade;
		this.valorDeVenda = valorDeVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
}
