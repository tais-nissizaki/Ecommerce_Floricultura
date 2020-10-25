package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Produto;

@Component
public class ValidarItemEmEstoque implements IStrategy {
	@Override
	public String processar(EntidadeDominio entidade) {
		String mensagem = "";
		if(entidade instanceof Produto) {
			Produto produto = (Produto) entidade;
			if(!validarQuantidadeProduto(produto)) {
				mensagem += "Você deve informar a quantidade";
			}
		}
		return mensagem;
	}
	
	private boolean validarQuantidadeProduto(Produto produto) {
		return produto.getItemEmEstoque()!=null && produto.getItemEmEstoque().getQuantidadeProduto()>0;
	}

}
