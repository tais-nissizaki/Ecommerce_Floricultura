package br.com.ecommerce.model.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "id")),
	@AttributeOverride(name = "dtCadastro", column = @Column(name = "dtCadastro")),
	@AttributeOverride(name = "dtAlteracao", column = @Column(name = "dtAlteracao"))
})
public class Cliente extends EntidadeDominio {
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="dtNasc")
	private Date dtNasc;
	
	@Column(name="genero")
	private Genero genero ;
	
	@Column(name="ativo")
	private boolean ativo;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="nome",column=@Column(name="nome")),
	})
	private Pessoa nome;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="classificacao",column=@Column(name="classificacao")),
	})
	private Classificacao classificacao;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEndereco")
	private Set<Endereco> endereco;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTelefone")
	private List<Telefone> telefone;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTransacao")
	private List<Transacao> transacao;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCartao")
	private List<Cartao> cartao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", targetEntity = Venda.class)
	private List<Venda> venda;
	
	public Cliente() {
	}
	
	public Cliente(Integer id) {
		setId(id);
	}

	public Cliente(Pessoa nome, String cpf, Usuario usuario) {
		this.nome = nome;
		this.cpf = cpf;
		this.usuario = usuario;
	}
	
	public Cliente(int id, Pessoa nome, String cpf, Usuario usuario) {
		this.nome = nome;
		this.cpf = cpf;
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Pessoa getNome() {
		return nome;
	}

	public void setNome(Pessoa nome) {
		this.nome = nome;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Transacao> getTransacao() {
		return transacao;
	}

	public void setTransacao(List<Transacao> transacao) {
		this.transacao = transacao;
	}

	public List<Cartao> getCartao() {
		return cartao;
	}

	public void setCartao(List<Cartao> cartao) {
		this.cartao = cartao;
	}

	public List<Venda> getVenda() {
		return venda;
	}

	public void setVenda(List<Venda> venda) {
		this.venda = venda;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

	
}
