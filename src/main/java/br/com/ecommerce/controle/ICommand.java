package br.com.ecommerce.controle;

import br.com.ecommerce.model.domain.EntidadeDominio;

public interface ICommand {
	
	public Object executar(EntidadeDominio entidade);
}
