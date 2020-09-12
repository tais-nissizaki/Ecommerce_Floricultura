package br.com.ecommerce.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fichaTecnica")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class FichaTecnica extends EntidadeDominio {
	
	@Column(name="nomeFicha")
	private String nomeFicha;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="observacoes")
	private String observacoes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSubcategoria")
	private Subcategoria subcategoria;
	
	@OneToMany
	@JoinColumn(name="idFichaTecnica")
	private Set<Componente> componentes; 

	@OneToMany
	@JoinColumn(name="idFichaTecnica")
	private Set<Acessorio> acessorios;
	
	@Column(name="ativo")
	private boolean ativo;

	public FichaTecnica(String nomeFicha, String descricao, Categoria categoria, Subcategoria subcategoria, Componente componenteBasico, Componente componentePrimario, Componente componenteSecundario) {
		this.nomeFicha = nomeFicha;
		this.descricao = descricao; 
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.componentes = new HashSet<>();
		this.componentes.add(componenteBasico);
		this.componentes.add(componentePrimario);
		this.componentes.add(componenteSecundario);
			
	}
	
	public FichaTecnica(int id) {
		setId(id);
			
	}
	
	public FichaTecnica(String nomeFicha, String descricao, String observacoes, Categoria categoria, Subcategoria subcategoria, Componente componenteBasico, Componente componentePrimario, Componente componenteSecundario) {
		this.nomeFicha = nomeFicha;
		this.descricao = descricao; 
		this.observacoes = observacoes;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.componentes = new HashSet<>();
		this.componentes.add(componenteBasico);
		this.componentes.add(componentePrimario);
		this.componentes.add(componenteSecundario);
			
	}

	public FichaTecnica() {
	}

	public String getNomeFicha() {
		return nomeFicha;
	}

	public void setNomeFicha(String nomeFicha) {
		this.nomeFicha = nomeFicha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricaoFicha) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Set<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(Set<Componente> componentes) {
		this.componentes = componentes;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Set<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(Set<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}
	
	public void adicionarAcessorio(Acessorio acessorio) {
		if(this.acessorios==null) {
			this.acessorios = new HashSet<>();
		}
		this.acessorios.add(acessorio);
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
