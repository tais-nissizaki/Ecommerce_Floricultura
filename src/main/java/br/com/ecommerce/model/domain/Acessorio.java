package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Acessorios")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Acessorio extends EntidadeDominio {
	
	@Column(name="nomeAcessorio")
	private String nomeAcessorio;
	
	@Column(name="descricaoAcessorio")
	private String descricaoAcessorio;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idItemEmEstoque")
	private ItemEmEstoque itemEmEstoque;
	
	@Column(name="ativo")
	private boolean ativo; 
	
	public Acessorio() {
		
	}

	public Acessorio(String nomeAcessorio, String descricaoAcessorio) {
		this.nomeAcessorio = nomeAcessorio;
		this.descricaoAcessorio = descricaoAcessorio;
	}

	public String getNomeAcessorio() {
		return nomeAcessorio;
	}

	public void setNomeAcessorio(String nomeAcessorio) {
		this.nomeAcessorio = nomeAcessorio;
	}

	public String getDescricaoAcessorio() {
		return descricaoAcessorio;
	}

	public void setDescricaoAcessorio(String descricaoAcessorio) {
		this.descricaoAcessorio = descricaoAcessorio;
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
