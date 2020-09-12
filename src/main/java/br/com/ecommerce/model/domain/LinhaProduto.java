package br.com.ecommerce.model.domain;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="linhaDeProdutos")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class LinhaProduto extends EntidadeDominio {
	
	@Column(name="nomeLinhaProduto")
	private String nomeLinhaProduto;

	@OneToMany
	@JoinColumn(name="idLinhaProduto")
	private List<Acessorio> acessorios;

	public LinhaProduto(String nomeLinhaProduto, List<Acessorio> acessorios) {
		this.nomeLinhaProduto = nomeLinhaProduto;
		this.acessorios = acessorios;
	}
	
	public LinhaProduto(String nomeLinhaProduto) {
		this.nomeLinhaProduto = nomeLinhaProduto;
	}

	public LinhaProduto() {
		
	}

	public String getNomeLinhaProduto() {
		return nomeLinhaProduto;
	}

	public void setNomeLinhaProduto(String nomeLinhaProduto) {
		this.nomeLinhaProduto = nomeLinhaProduto;
	}

	public List<Acessorio> getAcessorio() {
		return acessorios;
	}

	public void setAcessorio(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

}
