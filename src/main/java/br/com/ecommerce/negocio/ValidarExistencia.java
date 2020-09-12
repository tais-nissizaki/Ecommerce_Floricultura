package br.com.ecommerce.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerce.dao.ClienteDAO;
import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class ValidarExistencia implements IStrategy {
	
	@Autowired
	private ClienteDAO clienteDao;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		String mensagem ="";
		Cliente clienteFiltro = new Cliente();
		clienteFiltro.setCpf(cliente.getCpf());
		List<EntidadeDominio> clientes = clienteDao.consultar(clienteFiltro);
		if(clientes !=null && !clientes.isEmpty()) {
			mensagem += "Já existe cliente cadastrado para este CPF";
		}
		return mensagem;
	}
}
