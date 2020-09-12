package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.Endereco;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class ValidarCep implements IStrategy {
	@Override
	public String processar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		String mensagem ="";
		
		if(endereco.getCep() == null || endereco.getCep() == "" ) {
			mensagem += "O CEP é obrigatório.";
		}else if(endereco.getCep().replaceAll("\\D", "").length()!=8){
			mensagem += "O CEP deve ter 8 caracteres numéricos.";
		}
		return mensagem;
	}
}
