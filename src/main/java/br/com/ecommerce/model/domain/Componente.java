package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="componente")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Componente extends EntidadeDominio {
	
	@Column(name="nomeComponente")
	private String nomeComponente;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipo_componente")
	private TipoComponente tipoComponente;

	public Componente(String nomeComponente, TipoComponente tipoComponente) {
		this.nomeComponente = nomeComponente;
		this.tipoComponente = tipoComponente;
	}

	public String getNomeComponente() {
		return nomeComponente;
	}

	public void setNomeComponente(String nomeComponente) {
		this.nomeComponente = nomeComponente;
	}

	public TipoComponente getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
	}
}
