package br.com.ecommerce.negocio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.Cartao;
import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.Endereco;
import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Telefone;
import br.com.ecommerce.model.domain.TipoEndereco;

@Component
public class ValidarCliente implements IStrategy {
	
	@Autowired
	private ValidarExistencia vExistencia;
	
	@Autowired
	private ValidarCpf vCpf;
	
	@Autowired
	private ValidarTelefone vTelefone;
	
	@Autowired
	private ValidarEndereco vEndereco;
	
	@Autowired
	private ValidarUsuario vUsuario;
	
	@Autowired
	private ValidarCartao vCartao;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		String mensagem ="";
		mensagem += vCpf.processar(cliente);
		mensagem += vExistencia.processar(cliente);
		mensagem += vUsuario.processar(cliente.getUsuario());
		if(cliente.getNome()== null || cliente.getNome().equals("")) {
			mensagem += "O nome é obrigatório";
		}
		if(cliente.getDtNasc() == null) {
			mensagem += "O nome é obrigatório";
		} else if(cliente.getDtNasc().after(new Date())) {
			mensagem += "A data é inválida.";
		}
		if(cliente.getGenero() == null){
			mensagem += "O genero é um campo obrigatório";
		}
		if(cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
			mensagem += "O telefone é obrigatório.";
		}else {
			for(Telefone telefone:cliente.getTelefone()) {
				mensagem += vTelefone.processar(telefone);
			}
		}
		if(cliente.getEndereco()== null || cliente.getEndereco().isEmpty()) {
			mensagem += "É necessário ao menos um endereço de cobrança e um endereço de entrega";
		} else {
			boolean temEnderecoCobranca = false;
			boolean temEnderecoEntrega = false;
			for (Endereco endereco:cliente.getEndereco()) {
				mensagem += vEndereco.processar(endereco);
				if(endereco.getTipoEndereco().equals(TipoEndereco.COBRANCA)){
					temEnderecoCobranca = true;
				} else if (endereco.getTipoEndereco().equals(TipoEndereco.ENTREGA)) {
					temEnderecoEntrega = true;
				}
			}
			if(!temEnderecoCobranca) {
				mensagem += "É necessário ao menos um endereço de cobrança.";
			}
			if(!temEnderecoEntrega) {
				mensagem += "É necessário ao menos um endereço de entrega.";
			}
		}
		if(cliente.getId()>0) {
			if(cliente.getCartao()==null || cliente.getCartao().isEmpty()) {
				mensagem += "É necessário ao menos um cartão"; 
			} else {
				for(Cartao cartao: cliente.getCartao()) {
					mensagem += vCartao.processar(cartao);
				}
			}
		}
		
		 
		return mensagem;
	}
}
