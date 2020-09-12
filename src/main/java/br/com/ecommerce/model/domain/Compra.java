package br.com.ecommerce.model.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="compra")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Compra extends EntidadeDominio {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idComprador")
	private Comprador comprador;
	
	@Column(name="valorTotal")
	private double valorTotal;
	
	@Column(name="dtCompra")
	@Temporal(TemporalType.DATE)
	private Date dtCompra;

	public Compra(Comprador comprador, double valorTotal) {
		this.comprador = comprador;
		this.valorTotal = valorTotal;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDtCompra() {
		return dtCompra;
	}

	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}
	
}
