package br.com.ecommerce.negocio;

import br.com.ecommerce.model.domain.EntidadeDominio;

public interface IStrategy {

	public String processar(EntidadeDominio entidade);
	
}
