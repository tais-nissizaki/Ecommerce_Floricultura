package br.com.ecommerce.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ecommerce.model.domain.Carrinho;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Repository
public class CarrinhoDAO implements IDAO{

	@PersistenceContext
    private EntityManager entityManager;
	
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
		throw new RuntimeException("operação não permitida");
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Carrinho carrinho = (Carrinho)entidade;
		StringBuilder jpql = new StringBuilder();
		jpql.append("select c from Carrinho c ");
		jpql.append("where 1=1 ");
		
		Map <String, Object> parametros= new HashMap<>();
		if(carrinho.getCliente()!= null && carrinho.getCliente().getId() > 0) {
			jpql.append(" and c.cliente.id= :clienteId ");
			parametros.put("clienteId", carrinho.getCliente().getId());
		}
				
		Query query = entityManager.createQuery(jpql.toString());
		for(Entry<String, Object> entry: parametros.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}

	public void excluir(Carrinho carrinho) {
		Carrinho carrinhoGerenciado = entityManager.find(Carrinho.class, carrinho.getId());
		entityManager.remove(carrinhoGerenciado);
		
	}
}
