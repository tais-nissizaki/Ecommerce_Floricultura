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

import br.com.ecommerce.controle.ConsultarLoginCommand;
import br.com.ecommerce.controle.SalvarLoginCommand;
import br.com.ecommerce.model.domain.Usuario;

@RestController 
@RequestMapping("/login")
public class CtrlLogin {
	
	@Autowired
	private ConsultarLoginCommand consLoginCmd;
	
	@Autowired
	private SalvarLoginCommand salvarLoginCmd;

	@GetMapping
	public List <Usuario> obterLogins(){
		Usuario login= new Usuario();
		return (List<Usuario>) consLoginCmd.executar(login);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody Usuario login) {
		
		salvarLoginCmd.executar(login);
	}
}