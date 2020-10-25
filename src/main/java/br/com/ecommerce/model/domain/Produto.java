package br.com.ecommerce.model.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="urlFoto")
	private String urlFoto;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "valor")
	private BigDecimal valor; 
	
	@Column(name="ativo")
	private boolean ativo;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="largura",column=@Column(name="largura")),
		@AttributeOverride(name="altura",column=@Column(name="altura")),
		@AttributeOverride(name="diametro",column=@Column(name="diametro")),
	})
	private Dimensoes dimensoes;
	
	@ElementCollection(targetClass = Categoria.class)
	@JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "idProduto"))
	@Enumerated(EnumType.STRING)
	@Column(name = "categoria")
	private List <Categoria> categorias;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idGrupo")
	private GrupoPreco grupoPreco;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idItemDeCompra")
	private ItemDeCompra itemDeCompra;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idItemEmEstoque")
	private ItemEmEstoque itemEmEstoque;
	
	public Produto() {
		
	}
	
	public Produto(String nome, String descricao, List <Categoria> categorias) {
		this.nome = nome;
		this.descricao = descricao;
		this.categorias = categorias;
		
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Dimensoes getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(Dimensoes dimensoes) {
		this.dimensoes = dimensoes;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public GrupoPreco getGrupoPreco() {
		return grupoPreco;
	}

	public void setGrupoPreco(GrupoPreco grupoPreco) {
		this.grupoPreco = grupoPreco;
	}

}
