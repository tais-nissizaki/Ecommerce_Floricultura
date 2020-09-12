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
		if(!validarNomeProduto(produto)) {
			mensagem += "O nome do Produto � obrigat�rio.";
		}
		if(!validarValorDeCompra(produto)) {
			mensagem += "O valor de compra do produto � obrigat�rio.";
		}
		if(!validarLinhaProduto(produto)) {
			mensagem += "A  Linha do Produto � obrigat�ria.";
		}
		if(!validarFichaTecnica(produto)) {
			mensagem += "A Ficha T�cnica � obrigat�ria.";
		}
		
		return mensagem;
	}
	
	private boolean validarNomeProduto(Produto produto) {
		return produto.getNomeProduto()!= null && produto.getNomeProduto().trim()!= "";
	}
	
	private boolean validarValorDeCompra(Produto produto) {
		return produto.getItemDeCompra()!= null && produto.getItemDeCompra().getValorDeCompra()> 0;
	}
	
	private boolean validarLinhaProduto(Produto produto) {
		return produto.getLinhaProduto()!= null && produto.getLinhaProduto().getNomeLinhaProduto()!= null && produto.getLinhaProduto().getNomeLinhaProduto().trim()!= "";
	}
	
	private boolean validarFichaTecnica(Produto produto) {
		return produto.getFichaTecnica()!= null;
	}
}

