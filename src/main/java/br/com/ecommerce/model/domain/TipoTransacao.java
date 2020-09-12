package br.com.ecommerce.model.domain;

public enum TipoTransacao {
	
	COMPRA("COMPRA"),
	TROCA("TROCA");
	
	private String nomeTipoTransacao;
	
	private TipoTransacao(String nomeTipoTransacao) {
		this.nomeTipoTransacao = nomeTipoTransacao;
	}

	public String getNomeTipoTransacao() {
		return nomeTipoTransacao;
	}

}
