package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="transacoes")
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "id")),
	@AttributeOverride(name = "dtCadastro", column = @Column(name = "dtCadastro")),
	@AttributeOverride(name = "dtAlteracao", column = @Column(name = "dtAlteracao"))
})
public class Transacao extends EntidadeDominio {
	
	@Column(name="transacao")
	private String transacao;

	@Column(name="tipoTransacao")
	private TipoTransacao tipoTransacao;
	
	public Transacao(String transacao, TipoTransacao tipoTransacao) {
		this.transacao = transacao;
		this.tipoTransacao = tipoTransacao;
	}
	
	public Transacao() {
		
	}

	public String getTransacao() {
		return transacao;
	}

	public void setTransacao(String transacao) {
		this.transacao = transacao;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

}
