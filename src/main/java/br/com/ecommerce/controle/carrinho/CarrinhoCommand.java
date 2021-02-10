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
	public Integer adicionar(Produto produto, Integer qtd, Integer idCliente, Integer idCarrinho) {
		Cliente cliente = null;
		if(idCliente != null) {
			cliente = clienteDAO.obterPorId(idCliente);
		}

		ItemDeCarrinho itemDeCarrinho = new ItemDeCarrinho(qtd);
		List<EntidadeDominio> produtos = produtoDAO.consultar(produto);
		if(produtos != null && !produtos.isEmpty()) {
			itemDeCarrinho.setProduto((Produto)produtos.get(0));
		}
		
		Carrinho carrinho = null;
		
		if(idCarrinho == null) {
			carrinho = new Carrinho();
			carrinho.setCliente(cliente);
			carrinho.adicionarItemDeCarrinho(itemDeCarrinho);
			carrinhoDAO.salvar(carrinho);			
		} else {
			carrinho = carrinhoDAO.consultarPorId(idCarrinho);
			if(carrinho == null) {
				carrinho = new Carrinho();
				carrinho.setCliente(cliente);
			}
			boolean encontrou = false;
			for (int i=0; i < carrinho.getItensDeCarrinho().size(); i++ ) {
				ItemDeCarrinho itemDeCarrinhoGravado = carrinho.getItensDeCarrinho().get(i);
				if(itemDeCarrinhoGravado.getProduto().getId() == produto.getId()) {
					itemDeCarrinhoGravado.adicionaQuantidade(qtd);
					carrinhoDAO.alterar(carrinho);
					encontrou = true;
					break;
				}
			}
			if(!encontrou) {				
				carrinho.adicionarItemDeCarrinho(itemDeCarrinho);
				carrinhoDAO.alterar(carrinho);
			}
			
		}
		
		return carrinho.getId();
	}

	@Transactional(readOnly = true)
	public Carrinho obterCarrinho(int idCarrinho, int idCliente) {
		Cliente cliente = new Cliente(idCliente);
		Carrinho carrinho = new Carrinho(cliente);
		carrinho.setId(idCarrinho);
		List <EntidadeDominio> carrinhos = carrinhoDAO.consultar(carrinho);
		if(carrinhos != null && !carrinhos.isEmpty()) {
			Carrinho carrinhoRetorno = (Carrinho) carrinhos.get(0);
			for (int i=0; i< carrinhoRetorno.getItensDeCarrinho().size(); i++) {
				if(carrinhoRetorno.getCliente() != null) {
					carrinhoRetorno.getCliente().setUsuario(null);
					carrinhoRetorno.getCliente().setEndereco(null);
					carrinhoRetorno.getCliente().setTransacao(null);
					carrinhoRetorno.getCliente().setTelefone(null);
					carrinhoRetorno.getCliente().setVenda(null);
					carrinhoRetorno.getCliente().setCartao(null);
				}
				carrinhoRetorno.getItensDeCarrinho().get(i).setCarrinho(null);
				carrinhoRetorno.getItensDeCarrinho().get(i).getProduto().setItemDeCompra(null);
				carrinhoRetorno.getItensDeCarrinho().get(i).getProduto().setItemEmEstoque(null);
			}			
			return carrinhoRetorno;
		}else {
			return null;
		}
	}	
	
	@Transactional
	public Carrinho adicionarQuantidadeItemDeCarrinho(Integer idCarrinho, Produto produto, Integer quantidade) {
		Carrinho carrinho = carrinhoDAO.consultarPorId(idCarrinho);
		if(carrinho != null) {
			for(int i=0; i < carrinho.getItensDeCarrinho().size(); i++) {
				ItemDeCarrinho itemDeCarrinho = carrinho.getItensDeCarrinho().get(i);
				if(produto.getId() == itemDeCarrinho.getProduto().getId()) {
					itemDeCarrinho.setQuantidade(quantidade);
				}
			}
		}
		carrinhoDAO.alterar(carrinho);
		return carrinho;
	}

	@Transactional
	public Carrinho removerItemDeCarrinho(Integer idCarrinho, Integer produtoId) {
		Carrinho carrinho = carrinhoDAO.consultarPorId(idCarrinho);
		if(carrinho != null) {
			for(int i=0; i < carrinho.getItensDeCarrinho().size(); i++) {
				ItemDeCarrinho itemDeCarrinho = carrinho.getItensDeCarrinho().get(i);
				if(produtoId == itemDeCarrinho.getProduto().getId()) {
					carrinho.getItensDeCarrinho().remove(i);
					carrinhoDAO.excluirItemDeCarrinho(itemDeCarrinho);
				}
			}
		}
		return carrinho;
	}
	
}
