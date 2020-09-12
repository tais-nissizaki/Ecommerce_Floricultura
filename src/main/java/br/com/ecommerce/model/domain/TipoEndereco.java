package br.com.ecommerce.model.domain;

public enum TipoEndereco {
	
	ENTREGA("ENTREGA"),
	COBRANCA("COBRANCA");
	
	private String nomeTipoEndereco;
	
	private TipoEndereco(String nomeTipoEndereco) {
		this.nomeTipoEndereco = nomeTipoEndereco;
	}

	public String getNomeTipoComponente() {
		return nomeTipoEndereco;
	}

}
