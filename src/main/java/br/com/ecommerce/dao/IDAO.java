package br.com.ecommerce.dao;

import java.util.List;

import br.com.ecommerce.model.domain.EntidadeDominio;

public interface IDAO {
	
	public abstract boolean salvar(EntidadeDominio entidade);

	public abstract boolean alterar(EntidadeDominio entidade);

	public abstract boolean inativar(EntidadeDominio entidade);

	public abstract List<EntidadeDominio> consultar(EntidadeDominio entidade);
}
