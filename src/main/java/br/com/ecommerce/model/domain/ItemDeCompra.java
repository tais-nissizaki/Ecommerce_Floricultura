package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="itemDeCompra")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class ItemDeCompra extends EntidadeDominio {
	
	@Column(name="quantidade")
	private int quantidade;
	
	@Column(name="valorDeCompra")
	private double valorDeCompra;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCompra")
	private Compra compra;
	
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public ItemDeCompra(int quantidade, double valorDeCompra) {
		this.quantidade = quantidade;
		this.valorDeCompra = valorDeCompra;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorDeCompra() {
		return valorDeCompra;
	}

	public void setValorDeCompra(double valorDeCompra) {
		this.valorDeCompra = valorDeCompra;
	}
	
	
}
