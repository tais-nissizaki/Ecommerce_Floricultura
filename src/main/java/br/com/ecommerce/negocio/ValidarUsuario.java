package br.com.ecommerce.negocio;

import org.springframework.stereotype.Component;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Usuario;

@Component
public class ValidarUsuario implements IStrategy{
	@Override
	public String processar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		String mensagem = "";
		if(!validarSenha(usuario)) {
			mensagem += "Sua senha não é válida, é necessário letras maiúsculas, minúsculas e caracteres especiais.";
		}
		if(!confirmarSenha(usuario)) {
			mensagem += "A senha não confirma.";
		}
		return mensagem;
	}
	
	private boolean validarSenha(Usuario usuario){
		String letrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String letrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
		String caractereEspecial = "!@#$%&*-+=^~?";
		if(usuario.getSenha().length()<8) {
			return false;
		}
		boolean temMaiuscula = false;
		for(char c: usuario.getSenha().toCharArray()) {
			if(letrasMaiusculas.indexOf(c)>=0){
				temMaiuscula = true;
				break;
			}
		}
		if(!temMaiuscula) {
			return false;
		}
		boolean temMinuscula = false;
		for(char c: usuario.getSenha().toCharArray()) {
			if(letrasMinusculas.indexOf(c)>=0){
				temMinuscula = true;
				break;
			}
		}
		if(!temMinuscula) {
			return false;
		}
		boolean temEspecial = false;
		for(char c: usuario.getSenha().toCharArray()) {
			if(caractereEspecial.indexOf(c)>=0){
				temEspecial = true;
				break;
			}
		}
		if(!temEspecial) {
			return false;
		}
		return true;
	}
	
	private boolean confirmarSenha(Usuario usuario){
		return usuario.getSenha().equals(usuario.getConfirmaSenha());
	}
	
	private String validarLogin(Usuario usuario) {
		String mensagem = "";
		if(usuario.getLogin()== null || usuario.getLogin().equals("")) {
			mensagem += "O login é obrigatório. Informe um e-mail válido.";
		}
		return mensagem;
	}
}
