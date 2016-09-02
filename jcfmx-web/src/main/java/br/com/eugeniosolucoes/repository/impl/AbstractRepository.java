/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.repository.impl;

import br.com.eugeniosolucoes.repository.Entidade;
import br.com.eugeniosolucoes.repository.IRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eugenio
 * @param <T>
 * @param <K>
 */
public abstract class AbstractRepository<T extends Entidade<K>, K> implements IRepository<T, K> {

    @PersistenceContext
    protected EntityManager manager;

    protected Class<T> entityClass;

    public AbstractRepository( Class<T> entityClass ) {
        this.entityClass = entityClass;
    }

    @Override
    public T salvar( T entity ) {
        if ( entity.getId() == null ) {
            manager.persist( entity );
            return entity;
        } else {
            return manager.merge( entity );
        }
    }

    @Override
    public void excluir( T entity ) {
        manager.remove( manager.merge( entity ) );
    }

    @Override
    public void excluir( K id ) {
        manager.remove( retornar( id ) );
    }

    @Override
    public T retornar( K id ) {
        manager.clear();
        return manager.find( entityClass, id );
    }

    @Override
    public List<T> listar() {
        javax.persistence.criteria.CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select( cq.from( entityClass ) );
        return manager.createQuery( cq ).setFirstResult( 0 ).setMaxResults( 100 ).getResultList();
    }

    @Override
    public List<T> retornarFaixa( int[] range ) {
        javax.persistence.criteria.CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        cq.select( cq.from( entityClass ) );
        javax.persistence.Query q = manager.createQuery( cq );
        q.setMaxResults( range[1] - range[0] );
        q.setFirstResult( range[0] );
        return q.getResultList();
    }

    @Override
    public int contar() {
        javax.persistence.criteria.CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from( entityClass );
        cq.select( manager.getCriteriaBuilder().count( rt ) );
        javax.persistence.Query q = manager.createQuery( cq );
        return ( (Long) q.getSingleResult() ).intValue();
    }

}
