package br.com.ecommerce.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.model.domain.Venda;

@Repository
public class VendaDAO implements IDAO{

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
		Venda venda = (Venda)entidade;
		StringBuilder jpql = new StringBuilder();
		jpql.append("select v from Venda v ");
		jpql.append("where 1=1 ");
		Map<String, Object> params = new HashMap<>();
		if(venda.getCliente()!=null && venda.getCliente().getId() > 0) {
			jpql.append("and v.cliente.id = :idCliente ");
			params.put("idCliente", venda.getCliente().getId());
		}
		Query query = entityManager.createQuery(jpql.toString());
		if(!params.isEmpty()) {
			Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, Object> entry=iterator.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();
	}
}
