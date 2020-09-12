package br.com.ecommerce.negocio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerce.dao.LogDAO;
import br.com.ecommerce.model.Log;
import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class GerarLogCliente implements IStrategy{
		
	@Autowired
	private LogDAO logDao;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		Log log = new Log();
		
		System.err.println("Log gerado...");
		
		if(cliente.getId() <= 0) {
			log.setDtCadastro(new Date());
			log.setUsuario(cliente.getUsuario().getLogin());
			log.setDescricao("inclusão de um novo cliente");
		}
		
		logDao.salvar(log);
		
		return null;
	}
}
