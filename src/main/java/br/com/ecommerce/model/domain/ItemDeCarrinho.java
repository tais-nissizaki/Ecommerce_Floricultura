package br.com.ecommerce.model.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="itemDeCarrinho")

@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "id")),
	@AttributeOverride(name = "dtCadastro", column = @Column(name = "dtCadastro")),
	@AttributeOverride(name = "dtAlteracao", column = @Column(name = "dtAlteracao"))
})
public class ItemDeCarrinho extends EntidadeDominio {
	
	@Column(name="quantidade")
	private int quantidade;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "idCarrinho")
	private Carrinho carrinho;
	
	@OneToOne
	@JoinColumn(name = "idProduto")
	private Produto produto;
	
	public ItemDeCarrinho() {
		setDtCadastro(new Date());
	}

	public ItemDeCarrinho(int quantidade) {
		this();
		this.quantidade = quantidade;
	}

	public ItemDeCarrinho(int quantidade, Produto produto) {
		this(quantidade);
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
		public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	public void adicionaQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}
}
