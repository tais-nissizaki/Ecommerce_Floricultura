package br.com.ecommerce.controle;

import java.util.List;

import br.com.ecommerce.model.domain.EntidadeDominio;

public interface IFachada {
	
	public String salvar(EntidadeDominio entidade);
	public String alterar(EntidadeDominio entidade);
	public String inativar(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);
}
