package br.com.ecommerce.model.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Bandeira {
	
	private String bandeira;
	
	public Bandeira() {
	}
	
	public Bandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
}
