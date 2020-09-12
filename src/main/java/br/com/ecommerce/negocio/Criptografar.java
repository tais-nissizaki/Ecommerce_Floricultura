package br.com.ecommerce.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Usuario;

@Component
public class Criptografar implements IStrategy{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		Usuario usuario = cliente.getUsuario();
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return null;
	}
}
