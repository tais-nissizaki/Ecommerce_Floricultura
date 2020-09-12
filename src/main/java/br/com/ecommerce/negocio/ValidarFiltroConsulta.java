package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Produto;

@Component
public class ValidarFiltroConsulta implements IStrategy {
	@Override
	public String processar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		String mensagem = "";
		if(!validarCodigo(produto)&& !validarNomeProduto(produto) && !validarLinhaProduto(produto)&& !validarDescricao(produto)&& !validarComponente(produto)&& !validarCategoria(produto)&& !validarSubcategoria(produto)) {
			mensagem += "Voc� deve informar pelo menos um filtro";
		}
		return mensagem;
	}
	
	private boolean validarCodigo(Produto produto) {
		return produto.getCodigo()> 0;
	}
	
	private boolean validarNomeProduto(Produto produto) {
		return produto.getNomeProduto()!=null && produto.getNomeProduto().trim()!="";
	}
	
	private boolean validarLinhaProduto(Produto produto) {
		return produto.getLinhaProduto()!=null && produto.getLinhaProduto().getNomeLinhaProduto()!=null;
	}
	
	private boolean validarDescricao(Produto produto) {
		return produto.getFichaTecnica().getDescricao()!=null && produto.getFichaTecnica().getDescricao().trim()!="";
	}
	
	private boolean validarComponente(Produto produto) {
		return produto.getFichaTecnica().getComponentes()!=null && !produto.getFichaTecnica().getComponentes().isEmpty();
	}
	
	private boolean validarCategoria(Produto produto) {
		return produto.getFichaTecnica().getCategoria()!= null && produto.getFichaTecnica().getCategoria().getNomeCategoria().trim()!="";
	}
	
	private boolean validarSubcategoria(Produto produto) {
		return produto.getFichaTecnica().getSubcategoria()!= null && produto.getFichaTecnica().getSubcategoria().getNome().trim()!="";
	}
}
