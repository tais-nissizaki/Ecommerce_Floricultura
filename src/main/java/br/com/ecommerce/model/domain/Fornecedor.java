package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="fornecedor")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Fornecedor extends EntidadeDominio  {
	
	@Column(name="nomeFornecedor")
	private String nomeFornecedor;

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	
	
}
