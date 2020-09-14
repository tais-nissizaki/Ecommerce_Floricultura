package br.com.ecommerce.negocio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerce.dao.LogDAO;
import br.com.ecommerce.model.Log;
import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Usuario;

@Component
public class GerarLogUsuario implements IStrategy{
		
	@Autowired
	private LogDAO logDao;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		Log log = new Log();
		
		System.err.println("Log gerado...");
		
		if(usuario.getId() <= 0) {
			log.setDtCadastro(new Date());
			log.setUsuario(usuario.getLogin());
			log.setDescricao("inclusão de um novo usuario");
		} else {
			log.setDtAlteracao(new Date());
			log.setUsuario(usuario.getLogin());
			log.setDescricao("alteração de um novo usuario");
		}
		
		logDao.salvar(log);
		
		return null;
	}
}
