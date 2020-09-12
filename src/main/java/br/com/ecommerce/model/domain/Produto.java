package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="produtos")
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "id")),
	@AttributeOverride(name = "dtCadastro", column = @Column(name = "dtCadastro")),
	@AttributeOverride(name = "dtAlteracao", column = @Column(name = "dtAlteracao"))
})
public class Produto extends EntidadeDominio {
	
	@Column(name="codigoProduto")
	private int codigo;
	
	@Column(name="nomeProduto")
	private String nomeProduto;
	
	@Column(name="ativo")
	private boolean ativo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idLinhaProduto")
	private LinhaProduto linhaProduto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idFichaTecnica")
	private FichaTecnica fichaTecnica;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idItemDeCompra")
	private ItemDeCompra itemDeCompra;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idItemEmEstoque")
	private ItemEmEstoque itemEmEstoque;
	
	public Produto() {
	}

	public Produto(String nomeProduto, FichaTecnica fichaTecnica, LinhaProduto linhaProduto) {
		this.nomeProduto = nomeProduto;
		this.fichaTecnica = fichaTecnica;
		this.linhaProduto = linhaProduto;
	}
	
	public Produto(int codigo, String nomeProduto, FichaTecnica fichaTecnica, LinhaProduto linhaProduto) {
		this.codigo = codigo;
		this.nomeProduto = nomeProduto;
		this.fichaTecnica = fichaTecnica;
		this.linhaProduto = linhaProduto;
	}
	
	public Produto(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public LinhaProduto getLinhaProduto() {
		return linhaProduto;
	}

	public void setLinhaProduto(LinhaProduto linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	public FichaTecnica getFichaTecnica() {
		return fichaTecnica;
	}

	public void setFichaTecnica(FichaTecnica fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}
	
	public ItemDeCompra getItemDeCompra() {
		return itemDeCompra;
	}

	public void setItemDeCompra(ItemDeCompra itemDeCompra) {
		this.itemDeCompra = itemDeCompra;
	}
	
	public ItemEmEstoque getItemEmEstoque() {
		return itemEmEstoque;
	}

	public void setItemEmEstoque(ItemEmEstoque itemEmEstoque) {
		this.itemEmEstoque = itemEmEstoque;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
