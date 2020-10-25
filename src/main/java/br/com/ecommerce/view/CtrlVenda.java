package br.com.ecommerce.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.controle.venda.VendaCommand;
import br.com.ecommerce.model.domain.Carrinho;
import br.com.ecommerce.model.domain.Venda;

@RestController
@RequestMapping("/venda")
public class CtrlVenda {
	
	@Autowired
	private VendaCommand vendaCommand;
	
	@PostMapping
	public String incluir( @RequestBody Carrinho carrinho) {
		vendaCommand.adicionar(carrinho);
		return "ok";
	}
	
	@GetMapping
	public List <Venda> obterVendas( @RequestParam Integer idCliente){
		return vendaCommand.obterVendas(idCliente);
	}
	
}
