package br.com.ecommerce.model.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Pessoa {
	
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
}
