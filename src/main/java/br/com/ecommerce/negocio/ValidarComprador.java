package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Produto;

@Component
public class ValidarComprador implements IStrategy {
	@Override
	public String processar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		String mensagem ="";
		if(produto.getItemDeCompra().getCompra().getComprador() == null || produto.getItemDeCompra().getCompra().getComprador().getNomeComprador() == "" ) {
			mensagem += "O comprador � obrigat�rio.";
		}
		return mensagem;
	}
}
