package br.com.ecommerce.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Produto;

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
		jpql.append("where 1=1 ");
		
		Map <String, Object> parametros= new HashMap<>();
		if(produto.getNome()!= null && !produto.getNome().equals("")) {
			jpql.append(" and p.nome= :nome ");
			parametros.put("nome", produto.getNome());
		}
		if(produto.getId()> 0) {
			jpql.append(" and p.id= :idProduto ");
			parametros.put("idProduto", Integer.valueOf(produto.getId()));
		}
		
		Query query = entityManager.createQuery(jpql.toString());
		for(Entry<String, Object> entry: parametros.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}

	public Produto obterPorId(int id) {
		return entityManager.find(Produto.class, id);
		
	}

}
