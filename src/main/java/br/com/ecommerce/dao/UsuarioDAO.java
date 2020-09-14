package br.com.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.ecommerce.model.domain.EntidadeDominio;

@Repository
public class UsuarioDAO implements IDAO {

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
				
		return null;
	}

}
