package br.com.ecommerce.controle.venda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.dao.CarrinhoDAO;
import br.com.ecommerce.dao.ClienteDAO;
import br.com.ecommerce.dao.ProdutoDAO;
import br.com.ecommerce.dao.VendaDAO;
import br.com.ecommerce.model.domain.Carrinho;
import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.ItemDeCarrinho;
import br.com.ecommerce.model.domain.ItemDeVenda;
import br.com.ecommerce.model.domain.Produto;
import br.com.ecommerce.model.domain.Venda;

@Component
public class VendaCommand {
	
	@Autowired
	private CarrinhoDAO carrinhoDAO;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private VendaDAO vendaDAO;
	
	@Transactional
	public void adicionar(Carrinho carrinho) {
		Cliente cliente = clienteDAO.obterPorId(carrinho.getCliente().getId());
		Venda venda = new Venda(cliente, obterValorTotal(carrinho.getItensDeCarrinho()));
		for(int i =0; i < carrinho.getItensDeCarrinho().size(); i++) {
			ItemDeVenda itemDeVenda = new ItemDeVenda();
			Produto produto = produtoDAO.obterPorId(carrinho.getItensDeCarrinho().get(i).getProduto().getId());
			itemDeVenda.setProduto(produto);
			itemDeVenda.setQuantidade(carrinho.getItensDeCarrinho().get(i).getQuantidade());
			itemDeVenda.setVenda(venda);
			venda.adicionarItemDeVenda(itemDeVenda);
		}
		vendaDAO.salvar(venda);
		carrinhoDAO.excluir(carrinho);
	}

	private double obterValorTotal(List<ItemDeCarrinho> itensDeCarrinho) {
		double total= 0;
		for(int i =0; i < itensDeCarrinho.size(); i++) {
			total += itensDeCarrinho.get(i).getQuantidade()*itensDeCarrinho.get(i).getProduto().getValor().doubleValue();
		}
		return total;
	}

	@Transactional(readOnly = true)
	public List<Venda> obterVendas(Integer idCliente) {
		Cliente cliente = new Cliente(idCliente);
		Venda venda = new Venda(cliente, 0);
		List <EntidadeDominio> entidades = vendaDAO.consultar(venda);
		List <Venda> vendas = new ArrayList<>();
		for(int i=0; i < entidades.size(); i++) {
			Venda vendaResult = (Venda)entidades.get(i);
			vendaResult.getCliente().setUsuario(null);
			vendaResult.getCliente().setVenda(null);
			for(int j=0; j < vendaResult.getItensDeVenda().size(); j++) {
				vendaResult.getItensDeVenda().get(j).setVenda(null);
			}
			vendas.add(vendaResult);
		}
		return vendas;
	}	
	
}
