package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.Cartao;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class ValidarCartao implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Cartao cartao = (Cartao) entidade;
		String mensagem = "";
		if(cartao.getNumero()==null || cartao.getNumero().equals("")) {
			mensagem += "O número do cartão é obrigatório.";
		}
		if(cartao.getNomeImpresso()==null || cartao.getNomeImpresso().equals("")) {
			mensagem += "O nome impresso é obrigatório.";
		}
		if(cartao.getBandeira()==null || cartao.getBandeira().getBandeira()==null || cartao.getBandeira().getBandeira().equals("")) {
			mensagem += "A bandeira do cartão é obrigatório.";
		}
		if(cartao.getCodigoSeguranca()==null || cartao.getCodigoSeguranca().equals("")) {
			mensagem += "O código de segurança é obrigatório.";
		}
		if(cartao.getValidade()== null || cartao.getValidade().equals("")) {
			mensagem += "A validade do cartão é obrigatório";
		}
		if(cartao.getTipoCartao()==null) {
			mensagem += "O tipo do cartão é obrigatório";
		}
		return mensagem;
	}

}
