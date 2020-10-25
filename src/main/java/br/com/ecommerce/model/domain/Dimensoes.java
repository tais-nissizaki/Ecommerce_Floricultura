package br.com.ecommerce.model.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Dimensoes { 
	
	private String largura;
	
	private String altura;
	
	private String diametro;

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getDiametro() {
		return diametro;
	}

	public void setDiametro(String diametro) {
		this.diametro = diametro;
	}

}
