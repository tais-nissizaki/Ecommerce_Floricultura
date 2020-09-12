package br.com.ecommerce.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.controle.produto.AlterarProdutoCommand;
import br.com.ecommerce.controle.produto.ConsultarProdutoCommand;
import br.com.ecommerce.controle.produto.InativarProdutoCommand;
import br.com.ecommerce.controle.produto.SalvarProdutoCommand;
import br.com.ecommerce.model.domain.Categoria;
import br.com.ecommerce.model.domain.Componente;
import br.com.ecommerce.model.domain.FichaTecnica;
import br.com.ecommerce.model.domain.LinhaProduto;
import br.com.ecommerce.model.domain.Produto;
import br.com.ecommerce.model.domain.Subcategoria;
import br.com.ecommerce.model.domain.TipoComponente;

@RestController 
@RequestMapping("/produtos")
public class CtrlProduto {
	
	@Autowired
	private ConsultarProdutoCommand consProdCmd;
	
	@Autowired
	private SalvarProdutoCommand salvarProdCmd;
	
	@Autowired
	private AlterarProdutoCommand alterarProdCmd;
	
	@Autowired
	private InativarProdutoCommand inativarProdCmd;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void gravarProduto(@RequestBody Produto produto)  {
		salvarProdCmd.executar(produto);
	}
	
	@GetMapping
	public List <Produto> consultarProdutos(String txtNomeProduto, String txtLinhaProduto, String txtSubCategoria, 
			String txtCategoria, String txtComponenteBasico, String txtComponentePrimario, 
			String txtComponenteSecundario, String txtDescricao, String txtCodigoProduto){
		
		LinhaProduto linhaProduto = new LinhaProduto(txtLinhaProduto);
		Subcategoria subcategoria = new Subcategoria(txtSubCategoria);
		Categoria categoria = new Categoria(txtCategoria, subcategoria);
		
		Componente componenteBasico = new Componente(txtComponenteBasico, new TipoComponente(TipoComponente.BASICO));
		Componente componentePrimario = new Componente(txtComponentePrimario, new TipoComponente(TipoComponente.PRIMARIO));
		Componente componenteSecundario = new Componente(txtComponenteSecundario, new TipoComponente(TipoComponente.SECUNDARIO));
		
		
		FichaTecnica fichaTecnica = new FichaTecnica(null, txtDescricao, categoria, subcategoria, componenteBasico, 
				componentePrimario, componenteSecundario);
		
		List<Produto> produtos = new ArrayList<>();
		int codigo = 0;
		if(txtCodigoProduto!= null && !txtCodigoProduto.equals("")) {
			codigo = Integer.parseInt(txtCodigoProduto);
		}
		Produto produto = new Produto(codigo,txtNomeProduto, fichaTecnica, linhaProduto);
		produtos = (List<Produto>) consProdCmd.executar(produto);
		
		return produtos;
	}
	
	@PutMapping("/{idProduto}")
	public void alterar(@PathVariable int idProduto, @RequestBody Produto produto) {
		
		produto.setId(idProduto);
		alterarProdCmd.executar(produto);
	}
	
	@PatchMapping("/{idProduto}/inativar")
	public void inativar(@PathVariable int idProduto){
		
		Produto produto= new Produto();
		produto.setId(idProduto);
		inativarProdCmd.executar(produto);
	}
	
	@GetMapping("/{idProduto}")
	public Produto obterPorId(@PathVariable int idProduto) {
		
		Produto produto= new Produto();
		produto.setId(idProduto);
		List<Produto> produtos= (List<Produto>) consProdCmd.executar(produto);
		return produtos.get(0);
	}
}
