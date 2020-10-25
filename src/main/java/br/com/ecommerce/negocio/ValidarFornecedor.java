package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Produto;

@Component
public class ValidarFornecedor implements IStrategy {
	@Override
	public String processar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		String mensagem ="";
		if(produto.getItemDeCompra().getCompra().getFornecedor() == null || produto.getItemDeCompra().getCompra().getFornecedor().getNomeFornecedor() == "" ) {
			mensagem += "O fornecedor é obrigatório.";
		}
		return mensagem;
	}
}
