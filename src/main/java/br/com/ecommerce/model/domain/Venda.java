package br.com.ecommerce.model.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="venda")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Venda extends EntidadeDominio {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	@Column(name="valorTotal")
	private double valorTotal;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venda", fetch = FetchType.EAGER)
	private List <ItemDeVenda> itensDeVenda;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusVenda status;
	
	public Venda () {
	}
	
	public Venda(Cliente cliente, double valorTotal) {
		this.cliente = cliente;
		this.valorTotal = valorTotal;
		this.status = StatusVenda.EM_PROCESSAMENTO;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void adicionarItemDeVenda(ItemDeVenda itemDeVenda) {
		if(this.itensDeVenda==null) {
			this.itensDeVenda= new ArrayList();
		}
		this.itensDeVenda.add(itemDeVenda);	
		
	}

	public List<ItemDeVenda> getItensDeVenda() {
		return itensDeVenda;
	}

	public void setItensDeVenda(List<ItemDeVenda> itensDeVenda) {
		this.itensDeVenda = itensDeVenda;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

}
