package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cartoes")
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "id")),
	@AttributeOverride(name = "dtCadastro", column = @Column(name = "dtCadastro")),
	@AttributeOverride(name = "dtAlteracao", column = @Column(name = "dtAlteracao"))
})
public class Cartao extends EntidadeDominio {
	
	@Column(name="tipoCartao")
	private TipoCartao tipoCartao;
	
	@Column(name="numero")
	private String numero;
	
	@Column(name="validade")
	private String validade;
	
	@Column(name="nomeImpresso")
	private String nomeImpresso;
	
	@Column(name="codigoSeguranca")
	private String codigoSeguranca;
	
	@Column(name="bandeira")
	private Bandeira bandeira;

	public Cartao(TipoCartao tipoCartao, String numero, String validade, String nomeImpresso, String codigoSeguranca, Bandeira bandeira) {
		this.tipoCartao = tipoCartao;
		this.numero = numero;
		this.validade = validade;
		this.nomeImpresso = nomeImpresso;
		this.codigoSeguranca = codigoSeguranca;
		this.bandeira = bandeira;
	}
	
	public Cartao() {
		
	}

	public TipoCartao getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(TipoCartao tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getNomeImpresso() {
		return nomeImpresso;
	}

	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	

}
