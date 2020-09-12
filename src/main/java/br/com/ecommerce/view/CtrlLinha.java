package br.com.ecommerce.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.controle.ConsultarLinhaCommand;
import br.com.ecommerce.controle.SalvarLinhaCommand;
import br.com.ecommerce.model.domain.LinhaProduto;

@RestController 
@RequestMapping("/linha")
public class CtrlLinha {
	
	@Autowired
	private ConsultarLinhaCommand consLinhaCmd;
	
	@Autowired
	private SalvarLinhaCommand salvarLinhaCmd;

	@GetMapping
	public List <LinhaProduto> obterLinhaProdutos(){
		LinhaProduto linhaProduto= new LinhaProduto();
		return (List<LinhaProduto>) consLinhaCmd.executar(linhaProduto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody LinhaProduto linhaProduto) {
		
		salvarLinhaCmd.executar(linhaProduto);
	}
}
