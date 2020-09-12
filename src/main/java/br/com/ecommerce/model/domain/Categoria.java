package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Categoria extends EntidadeDominio {
	
	@Column(name="nomeCategoria")
	private String nomeCategoria;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSubcategoria")
	private Subcategoria subcategoria;

	public Categoria(String nomeCategoria, Subcategoria subcategoria) {
		this.nomeCategoria = nomeCategoria;
		this.subcategoria = subcategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
}
