package br.com.ecommerce.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="carrinho")

@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "id")),
	@AttributeOverride(name = "dtCadastro", column = @Column(name = "dtCadastro")),
	@AttributeOverride(name = "dtAlteracao", column = @Column(name = "dtAlteracao"))
})public class Carrinho extends EntidadeDominio {
	
	@OneToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho", fetch = FetchType.EAGER)
	private List <ItemDeCarrinho> itensDeCarrinho;
	
	public Carrinho () {
		setDtCadastro(new Date());
	}
	
	public Carrinho(Cliente cliente) {
		this();
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemDeCarrinho> getItensDeCarrinho() {
		return itensDeCarrinho;
	}

	public void setItensDeCarrinho(List<ItemDeCarrinho> itensDeCarrinho) {
		this.itensDeCarrinho = itensDeCarrinho;
	}
	
	public void adicionarItemDeCarrinho(ItemDeCarrinho itemDeCarrinho) {
		if(this.itensDeCarrinho == null ) {
			this.itensDeCarrinho = new ArrayList();
		}
		itemDeCarrinho.setCarrinho(this);
		this.itensDeCarrinho.add(itemDeCarrinho);
	}

}
