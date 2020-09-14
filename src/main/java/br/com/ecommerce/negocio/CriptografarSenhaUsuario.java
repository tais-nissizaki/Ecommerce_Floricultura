package br.com.ecommerce.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Usuario;

@Component
public class CriptografarSenhaUsuario implements IStrategy{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return null;
	}
}
