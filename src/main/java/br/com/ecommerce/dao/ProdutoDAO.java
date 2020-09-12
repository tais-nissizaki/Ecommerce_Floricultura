package br.com.ecommerce.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ecommerce.model.domain.Componente;
import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Produto;
import br.com.ecommerce.model.domain.TipoComponente;

@Repository
public class ProdutoDAO implements IDAO {

    @PersistenceContext
    private EntityManager entityManager;
	@Override
	public boolean salvar(EntidadeDominio entidade) {
		try{
			entityManager.persist(entidade);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean alterar(EntidadeDominio entidade) {
		try{
			entityManager.merge(entidade);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean inativar(EntidadeDominio entidade) {
		try{
			entityManager.merge(entidade);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Produto produto = (Produto)entidade;
		StringBuilder jpql = new StringBuilder();
		jpql.append("select p from Produto p ");
		jpql.append("left join fetch p.linhaProduto linha ");
		jpql.append("left join fetch p.fichaTecnica ficha ");
		jpql.append("left join fetch ficha.acessorios a ");
		jpql.append("left join fetch ficha.componentes componente ");
		jpql.append("where 1=1 ");
		
		Map <String, Object> parametros= new HashMap<>();
		if(produto.getNomeProduto()!= null && !produto.getNomeProduto().equals("")) {
			jpql.append(" and p.nomeProduto= :nomeProduto ");
			parametros.put("nomeProduto", produto.getNomeProduto());
		}
		if(produto.getItemDeCompra()!= null && produto.getItemDeCompra().getValorDeCompra()> 0) {
			jpql.append(" and p.itemDeCompra.valorDeCompra= :valorDeCompra ");
			parametros.put("valorDeCompra", produto.getItemDeCompra().getValorDeCompra());
		}
		if(produto.getLinhaProduto()!= null && produto.getLinhaProduto().getNomeLinhaProduto()!= null && !produto.getLinhaProduto().getNomeLinhaProduto().equals("")) {
			jpql.append(" and linha.nomeLinhaProduto= :nomeLinhaProduto ");
			parametros.put("nomeLinhaProduto", produto.getLinhaProduto().getNomeLinhaProduto());
		}
		if(produto.getFichaTecnica() != null && produto.getFichaTecnica().getId() > 0) {
			jpql.append(" and ficha.id= :idFicha ");
			parametros.put("idFicha", produto.getFichaTecnica().getId());
		}
		if(produto.getCodigo()> 0) {
			jpql.append(" and p.codigo= :codigo ");
			parametros.put("codigo", Integer.valueOf(produto.getCodigo()));
		}
		if(produto.getId()> 0) {
			jpql.append(" and p.id= :idProduto ");
			parametros.put("idProduto", Integer.valueOf(produto.getId()));
		}
		if(produto.getFichaTecnica() != null && produto.getFichaTecnica().getDescricao() != null && !produto.getFichaTecnica().getDescricao().equals("")) {
			jpql.append(" and ficha.descricao= :descricao ");
			parametros.put("descricao", produto.getFichaTecnica().getDescricao());
		}
		if(produto.getFichaTecnica() != null && produto.getFichaTecnica().getCategoria() != null && produto.getFichaTecnica().getCategoria().getNomeCategoria() != null 
				&& !produto.getFichaTecnica().getCategoria().getNomeCategoria().equals("")) {
			jpql.append(" and ficha.categoria.nomeCategoria= :nomeCategoria ");
			parametros.put("nomeCategoria", produto.getFichaTecnica().getCategoria().getNomeCategoria());
		}
		if(produto.getFichaTecnica() != null && produto.getFichaTecnica().getSubcategoria() != null && produto.getFichaTecnica().getSubcategoria().getNome() != null 
				&& !produto.getFichaTecnica().getSubcategoria().getNome().equals("")) {
			jpql.append(" and ficha.subcategoria.nome= :nomeSubcategoria ");
			parametros.put("nomeSubcategoria", produto.getFichaTecnica().getSubcategoria().getNome());
		}
		if(produto.getFichaTecnica() != null && produto.getFichaTecnica().getComponentes() != null && !produto.getFichaTecnica().getComponentes().isEmpty()) {
			for (Componente	componente : produto.getFichaTecnica().getComponentes()) {
				if(componente.getNomeComponente() != null && !componente.getNomeComponente().equals("")) {
					jpql.append(" and componente.nomeComponente= :nomeComponente ");
					jpql.append(" and componente.tipoComponente.nomeTipoComponente= :nomeTipoComponente ");
						
					parametros.put("nomeComponente", componente.getNomeComponente());
					parametros.put("nomeTipoComponente", componente.getTipoComponente().getNomeTipoComponente());
				}
			}
		}
		
		Query query = entityManager.createQuery(jpql.toString());
		for(Entry<String, Object> entry: parametros.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}

}
