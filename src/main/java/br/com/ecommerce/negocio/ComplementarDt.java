package br.com.ecommerce.negocio;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class ComplementarDt implements IStrategy{
	
	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade.getId() > 0) {
			entidade.setDtAlteracao(new Date());
		} else {
			entidade.setDtCadastro(new Date());
		}
		return null;
	}

}