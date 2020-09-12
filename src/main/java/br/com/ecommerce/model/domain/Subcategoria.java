package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="subcategoria")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Subcategoria extends EntidadeDominio {
	
	@Column(name="nomeSubcategoria")
	private String nome;

	public Subcategoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
