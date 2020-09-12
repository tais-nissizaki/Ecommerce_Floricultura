package br.com.ecommerce.model.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Classificacao { 
	
	private int classificacao;

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
}