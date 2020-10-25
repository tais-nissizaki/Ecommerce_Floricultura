package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Produto;

@Component
public class ValidarProduto implements IStrategy{
	@Override
	public String processar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		String mensagem = "";
		if(!validarNome(produto)) {
			mensagem += "O nome do Produto é obrigatório.";
		}
		if(!validarValorDeCompra(produto)) {
			mensagem += "O valor de compra do produto é obrigatório.";
		}
			
		return mensagem;
	}
	
	private boolean validarNome(Produto produto) {
		return produto.getNome()!= null && produto.getNome().trim()!= "";
	}
	
	private boolean validarValorDeCompra(Produto produto) {
		return produto.getItemDeCompra()!= null && produto.getItemDeCompra().getValorDeCompra()> 0;
	}
	
}

