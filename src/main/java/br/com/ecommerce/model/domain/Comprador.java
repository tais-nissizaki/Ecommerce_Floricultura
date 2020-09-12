package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="comprador")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Comprador extends EntidadeDominio  {
	
	@Column(name="nomeComprador")
	private String nomeComprador;

	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}
	
	
}
