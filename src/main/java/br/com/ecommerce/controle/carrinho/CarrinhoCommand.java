package br.com.ecommerce.controle.carrinho;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.dao.CarrinhoDAO;
import br.com.ecommerce.dao.ClienteDAO;
import br.com.ecommerce.dao.ProdutoDAO;
import br.com.ecommerce.model.domain.Carrinho;
import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.ItemDeCarrinho;
import br.com.ecommerce.model.domain.Produto;

@Component
public class CarrinhoCommand {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private CarrinhoDAO carrinhoDAO;
	
	@Transactional
	public void adicionar(Produto produto, Integer qtd, Integer idCliente) {
		Cliente cliente = clienteDAO.obterPorId(idCliente);
		ItemDeCarrinho itemDeCarrinho = new ItemDeCarrinho(qtd, produto);
		Carrinho carrinho = new Carrinho();
		carrinho.setCliente(cliente);
		List<EntidadeDominio> produtos = produtoDAO.consultar(produto);
		if(produtos != null && !produtos.isEmpty()) {
			itemDeCarrinho.setProduto((Produto)produtos.get(0));
		}
		List<EntidadeDominio> carrinhos = carrinhoDAO.consultar(carrinho);
		if(carrinhos == null || carrinhos.isEmpty()) {
			carrinho.adicionarItemDeCarrinho(itemDeCarrinho);
			itemDeCarrinho.setCarrinho(carrinho);
			carrinhoDAO.salvar(carrinho);
			
		} else {
			carrinho = (Carrinho) carrinhos.get(0);
			carrinho.adicionarItemDeCarrinho(itemDeCarrinho);
			carrinhoDAO.alterar(carrinho);
		}
	}

	@Transactional(readOnly = true)
	public Carrinho obterCarrinho(Integer idCliente) {
		Cliente cliente = new Cliente(idCliente);
		Carrinho carrinho = new Carrinho(cliente);
		List <EntidadeDominio> carrinhos = carrinhoDAO.consultar(carrinho);
		if(carrinhos != null && !carrinhos.isEmpty()) {
			Carrinho carrinhoRetorno = (Carrinho) carrinhos.get(0);
			for (int i=0; i< carrinhoRetorno.getItensDeCarrinho().size(); i++) {
				carrinhoRetorno.getCliente().setUsuario(null);
				carrinhoRetorno.getCliente().setEndereco(null);
				carrinhoRetorno.getCliente().setTransacao(null);
				carrinhoRetorno.getCliente().setTelefone(null);
				carrinhoRetorno.getCliente().setVenda(null);
				carrinhoRetorno.getCliente().setCartao(null);
				carrinhoRetorno.getItensDeCarrinho().get(i).setCarrinho(null);
				carrinhoRetorno.getItensDeCarrinho().get(i).getProduto().setItemDeCompra(null);
				carrinhoRetorno.getItensDeCarrinho().get(i).getProduto().setItemEmEstoque(null);
			}			
			return carrinhoRetorno;
		}else {
			return null;
		}
	}	
	
}
