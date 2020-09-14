package br.com.ecommerce.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.controle.AlterarUsuarioCommand;
import br.com.ecommerce.controle.cliente.AlterarClienteCommand;
import br.com.ecommerce.controle.cliente.ConsultarClienteCommand;
import br.com.ecommerce.controle.cliente.InativarClienteCommand;
import br.com.ecommerce.controle.cliente.SalvarClienteCommand;
import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.Pessoa;
import br.com.ecommerce.model.domain.Usuario;

@RestController 
@RequestMapping("/clientes")
public class CtrlCliente {
	
	@Autowired
	private ConsultarClienteCommand consCliCmd;
	
	@Autowired
	private SalvarClienteCommand salvarCliCmd;
	
	@Autowired
	private AlterarClienteCommand alterarCliCmd;
	
	@Autowired
	private AlterarUsuarioCommand alterarUsuCmd;
	
	@Autowired
	private InativarClienteCommand inativarCliCmd;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void gravarCliente(@RequestBody Cliente cliente)  {
		salvarCliCmd.executar(cliente);
	}
	
	@GetMapping
	public List <Cliente> consultarClientes(String txtNomeCliente, String txtCpf, String txtLogin, Integer idCliente){
		
		List<Cliente> clientes = new ArrayList<>();
		
		Usuario usuario = new Usuario();
		Pessoa pessoa = new Pessoa();
		Cliente cliente = new Cliente();
		pessoa.setNome(txtNomeCliente);
		cliente.setNome(pessoa);
		cliente.setCpf(txtCpf);
		usuario.setLogin(txtLogin);
		cliente.setUsuario(usuario);
		if(idCliente!=null) {
			cliente.setId(idCliente);
		}
		
		
		clientes = (List<Cliente>) consCliCmd.executar(cliente);
		
		return clientes;
	}
	
	@PutMapping("/{idCliente}")
	public void alterar(@PathVariable int idCliente, @RequestBody Cliente cliente) {
		
		cliente.setId(idCliente);
		alterarCliCmd.executar(cliente);
	}
	
	@PutMapping("/alterar-senha/{idUsuario}")
	public void alterar(@PathVariable int idUsuario, @RequestBody Usuario usuario) {
		usuario.setId(idUsuario);
		alterarUsuCmd.executar(usuario);
	}
	
	@PatchMapping("/{idCliente}/inativar")
	public void inativar(@PathVariable int idCliente){
		
		Cliente cliente= new Cliente();
		cliente.setId(idCliente);
		inativarCliCmd.executar(cliente);
	}
	
}
