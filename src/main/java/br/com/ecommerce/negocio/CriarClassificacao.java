package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.Classificacao;
import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class CriarClassificacao implements IStrategy{
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		if(cliente.getId()<=0) {
			cliente.setClassificacao(new Classificacao());
		}
		return null;
	}
}
