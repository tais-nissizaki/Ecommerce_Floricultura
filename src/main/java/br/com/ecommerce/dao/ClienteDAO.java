package br.com.ecommerce.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.EntidadeDominio;

@Repository
public class ClienteDAO implements IDAO {

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
		Cliente cliente = (Cliente)entidade;
		StringBuilder jpql = new StringBuilder();
		jpql.append("select c from Cliente c ");
		jpql.append("left join c.usuario ");
		jpql.append("where 1=1 ");
		
		Map <String, Object> parametros= new HashMap<>();
		if(cliente.getNome()!= null && cliente.getNome().getNome()!= null && !cliente.getNome().getNome().equals("")) {
			jpql.append(" and c.nome= :nome ");
			parametros.put("nome", cliente.getNome());
		}
		if(cliente.getUsuario() != null && cliente.getUsuario().getLogin() != null && !cliente.getUsuario().getLogin().equals("")) {
			jpql.append(" and c.usuario.login= :login ");
			parametros.put("login", cliente.getUsuario().getLogin());
		}
		if(cliente.getCpf()!= null && !cliente.getCpf().equals("")) {
			jpql.append(" and c.cpf= :cpf ");
			parametros.put("cpf", cliente.getCpf());
		}
		if(cliente.getId() > 0) {
			jpql.append(" and c.id= :id ");
			parametros.put("id", cliente.getId());
		}
				
		Query query = entityManager.createQuery(jpql.toString());
		for(Entry<String, Object> entry: parametros.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}
	
	public Cliente obterPorId(Integer id) {
		return entityManager.find(Cliente.class, id);
	}

}
