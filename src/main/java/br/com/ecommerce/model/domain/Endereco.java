package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="enderecos")
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "id")),
	@AttributeOverride(name = "dtCadastro", column = @Column(name = "dtCadastro")),
	@AttributeOverride(name = "dtAlteracao", column = @Column(name = "dtAlteracao"))
})
public class Endereco extends EntidadeDominio {

	@Column(name="tipoLogradouro")
	private String tipoLogradouro;
	
	@Column(name="logradouro")
	private String logradouro;
	
	@Column(name="numero")
	private String numero;
	
	/**
	 * Tipo da residência identifica se a a residência é casa, apartamento, etc
	 */
	@Column(name="tipoResidencia")
	private String tipoResidencia;
	
	@Column(name="observacoes")
	private String observacoes;
	
	/**
	 * Descrição é um nome composto de uma frase curta para identificar o endereço
	 * exemplos: casa da mãe, minha casa, apto do Zé
	 */
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="bairro")
	private String bairro;
	
	/**
	 * entrega ou cobrança
	 */
	@Column(name="tipoEndereco")
	private TipoEndereco tipoEndereco;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="cidade",column=@Column(name="cidade")),
		@AttributeOverride(name="estado",column=@Column(name="estado")),
	})
	private Cidade cidade;
	
	public Endereco() {
		
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipoResidencia() {
		return tipoResidencia;
	}

	public void setTipoResidencia(String tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	

}
