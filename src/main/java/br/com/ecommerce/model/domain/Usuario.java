package br.com.ecommerce.model.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Usuario extends EntidadeDominio {
	
	@Column(name="login")
	private String login;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="confirmaSenha")
	private String confirmaSenha;
	
	@Column(name="ativo")
	private boolean ativo;

	public Usuario(String login, String senha, String confirmaSenha) {
		this.login = login;
		this.senha = senha;
		this.confirmaSenha = confirmaSenha;
	}
	
	public Usuario(String login) {
		this.login = login;
	}

	public Usuario() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmacaoSenha) {
		this.confirmaSenha = confirmacaoSenha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
