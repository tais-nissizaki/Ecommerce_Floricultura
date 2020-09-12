package br.com.ecommerce.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.Endereco;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Component
public class ValidarEndereco implements IStrategy {
	
	@Autowired
	private ValidarCep validarCep;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		String mensagem ="";
		
		if(endereco.getTipoResidencia() == null || endereco.getTipoResidencia() == "" ) {
			mensagem += "O tipo da residência é obrigatório. Informe se sua residência é casa, apartamento, etc";
		}
		if(endereco.getTipoLogradouro() == null || endereco.getTipoLogradouro() == "" ) {
			mensagem += "O tipo do Logradouro é obrigatório.";
		}
		if(endereco.getLogradouro() == null || endereco.getLogradouro() == "" ) {
			mensagem += "O logradouro é obrigatório.";
		}
		if(endereco.getNumero() == null || endereco.getNumero() == "" ) {
			mensagem += "O número é obrigatório.";
		}
		
		if(endereco.getDescricao() == null || endereco.getDescricao() == "" ) {
			mensagem += "A descrição é obrigatório.";
		}
		if(endereco.getBairro() == null || endereco.getBairro() == "" ) {
			mensagem += "O bairro é obrigatório.";
		}
		mensagem += validarCep.processar(endereco);		
		if(endereco.getCidade() == null || endereco.getCidade().getCidade() == null || endereco.getCidade().getCidade() == "" ) {
			mensagem += "A cidade é obrigatória.";
		}
		if(endereco.getCidade() != null && endereco.getCidade().getEstado() == null ) {
			mensagem += "O estado é obrigatório.";
		}
		
		return mensagem;
	}
}
