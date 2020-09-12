package br.com.ecommerce.model.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="itemEmEstoque")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class ItemEmEstoque extends EntidadeDominio {
	
	@Column(name="quantidadeProduto")
	private int quantidadeProduto;
	
	@Column(name="dtEntrada")
	@Temporal(TemporalType.DATE)
	private Date dtEntrada;
	
	@Column(name="quantidadeAcessorio")
	private int quantidadeAcessorio;

	public ItemEmEstoque(int qtdProduto, Date dtEntrada, int qtdAcessorio) {
		this.quantidadeProduto = qtdProduto;
		this.dtEntrada = dtEntrada;
		this.quantidadeAcessorio = qtdAcessorio;
	}

	public int getQuantidadeAcessorio() {
		return quantidadeAcessorio;
	}

	public void setQuantidadeAcessorio(int quantidadeAcessorio) {
		this.quantidadeAcessorio = quantidadeAcessorio;
	}

	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Date getDtEntrada() {
		return dtEntrada;
	}

	public void setDtEntrada(Date dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

}
