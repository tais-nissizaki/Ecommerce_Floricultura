package br.com.ecommerce.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class ConsultarFichaCommand implements ICommand {
	
	@Autowired
	private IFachada fachada;

	@Override
	public Object executar(EntidadeDominio entidade) {
		
		return fachada.consultar(entidade);
	}

}
