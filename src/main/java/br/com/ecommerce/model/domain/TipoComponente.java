package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tipoComponente")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class TipoComponente extends EntidadeDominio {
	
	@Column(name="nomeTipoComponente")
	private String nomeTipoComponente;
	
	public static final String BASICO = "BASICO";
	public static final String PRIMARIO = "PRIMARIO";
	public static final String SECUNDARIO = "SECUNDARIO";

	public TipoComponente(String nomeTipoComponente) {
		this.nomeTipoComponente = nomeTipoComponente;
	}

	public String getNomeTipoComponente() {
		return nomeTipoComponente;
	}

	public void setNomeTipoComponente(String nomeTipoComponente) {
		this.nomeTipoComponente = nomeTipoComponente;
	}

}
