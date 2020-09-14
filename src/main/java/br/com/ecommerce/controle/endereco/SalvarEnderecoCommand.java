package br.com.ecommerce.controle.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerce.controle.ICommand;
import br.com.ecommerce.controle.IFachada;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class SalvarEnderecoCommand implements ICommand {
	
	@Autowired
	private IFachada fachada;

	@Override
	public Object executar(EntidadeDominio entidade) {
		
		return fachada.salvar(entidade);
	}

}
