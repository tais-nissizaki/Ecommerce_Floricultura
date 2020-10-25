package br.com.ecommerce.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String adicionarProduto( @RequestBody Produto produto, @RequestParam Integer qtd, @RequestParam Integer idCliente) {
		carrinhoCommand.adicionar(produto, qtd, idCliente);
		return "ok";
	}
	
	@GetMapping
	public Carrinho obterCarrinho(@RequestParam Integer idCliente){
		return carrinhoCommand.obterCarrinho(idCliente);
	}
}
