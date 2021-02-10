package br.com.ecommerce.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.controle.carrinho.CarrinhoCommand;
import br.com.ecommerce.model.domain.Carrinho;
import br.com.ecommerce.model.domain.Produto;

//Identifica esta classe como se fosse um controlador de requisição rest
// associa o mapeamento de /carrinho para esta classe
@RestController
@RequestMapping("/carrinho")
public class CtrlCarrinho {
	
	//injeta a dependência do carrinho command
	@Autowired
	private CarrinhoCommand carrinhoCommand;
	
	//mapeia uma requisição post
	//conteúdo do corpo da requisição
	// parâmetro na url da requisição
	@PostMapping
	public Integer adicionarProduto( @RequestBody Produto produto, @RequestParam Integer qtd, 
			@RequestParam(required = false) Integer idCliente, @RequestParam(required = false) Integer idCarrinho) {
		return carrinhoCommand.adicionar(produto, qtd, idCliente, idCarrinho);
	}
	
	@GetMapping
	public Carrinho obterCarrinho(@RequestParam(required = false) Integer idCarrinho){
		int idCarrinhoInt = 0;
		if(idCarrinho != null ) {
			idCarrinhoInt = idCarrinho;
		}
		return carrinhoCommand.obterCarrinho(idCarrinhoInt, 0);
	}
	
	@PutMapping("/{idCarrinho}")
	public Carrinho adicionarQuantidade(@RequestBody Produto produto, @PathVariable Integer idCarrinho, @RequestParam Integer qtd) {
		Carrinho carrinho = carrinhoCommand.adicionarQuantidadeItemDeCarrinho(idCarrinho, produto, qtd);
		if(carrinho.getCliente() != null) {
			carrinho.getCliente().setUsuario(null);
			carrinho.getCliente().setEndereco(null);
			carrinho.getCliente().setTransacao(null);
			carrinho.getCliente().setTelefone(null);
			carrinho.getCliente().setVenda(null);
			carrinho.getCliente().setCartao(null);
		}
		for(int i=0; i < carrinho.getItensDeCarrinho().size(); i++) {
			carrinho.getItensDeCarrinho().get(i).setCarrinho(null);
			carrinho.getItensDeCarrinho().get(i).getProduto().setItemDeCompra(null);
			carrinho.getItensDeCarrinho().get(i).getProduto().setItemEmEstoque(null);
		}
		return carrinho;
	}
	
	@DeleteMapping("/{idCarrinho}/remover-item/{produtoId}")
	public Carrinho removerItemDeCarrinho(@PathVariable Integer idCarrinho, @PathVariable Integer produtoId) {
		Carrinho carrinho = carrinhoCommand.removerItemDeCarrinho(idCarrinho, produtoId);
		if(carrinho.getCliente() != null) {
			carrinho.getCliente().setUsuario(null);
			carrinho.getCliente().setEndereco(null);
			carrinho.getCliente().setTransacao(null);
			carrinho.getCliente().setTelefone(null);
			carrinho.getCliente().setVenda(null);
			carrinho.getCliente().setCartao(null);
		}
		for(int i=0; i < carrinho.getItensDeCarrinho().size(); i++) {
			carrinho.getItensDeCarrinho().get(i).setCarrinho(null);
			carrinho.getItensDeCarrinho().get(i).getProduto().setItemDeCompra(null);
			carrinho.getItensDeCarrinho().get(i).getProduto().setItemEmEstoque(null);
		}
		return carrinho;
	}
}
