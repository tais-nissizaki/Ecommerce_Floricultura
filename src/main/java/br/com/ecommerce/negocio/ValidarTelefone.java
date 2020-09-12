package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Telefone;

@Component
public class ValidarTelefone implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;
		String mensagem ="";
		if(telefone.getTipoTelefone() == null) {
			mensagem += "O tipo do telefone é obrigatório";
		}
		if(telefone.getDdd() == null || telefone.getDdd().equals("")) {
			mensagem += "O DDD é obrigatório";
		}
		if(telefone.getNumero() == null || telefone.getNumero().equals("")) {
			mensagem += "O número do telefone é obrigatório";
		}
		return mensagem;
	}

}
