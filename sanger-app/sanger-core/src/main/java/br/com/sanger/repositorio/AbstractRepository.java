/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.sanger.repositorio.IRepository;

/**
 *
 * @author eugenio
 */
public abstract class AbstractRepository<T> implements IRepository<T> {

    protected EntityManagerFactory factory;

    protected EntityManager em;

    public static final String PERSISTENCE_UNIT_NAME = "SANGER_PU";

    private Class<T> entityClass;

    public AbstractRepository( Class<T> entityClass ) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void criar( T entity ) throws Exception {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist( entity );
        getEntityManager().getTransaction().commit();
    }

    public void editar( T entity ) throws Exception {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge( entity );
        getEntityManager().getTransaction().commit();
    }

    public void excluir( T entity ) throws Exception {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove( getEntityManager().merge( entity ) );
        getEntityManager().getTransaction().commit();
    }

    public T retornar( Object id ) throws Exception {
        getEntityManager().clear();
        return getEntityManager().find( entityClass, id );
    }

    public List<T> listar() throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select( cq.from( entityClass ) );
        return getEntityManager().createQuery( cq ).getResultList();
    }

    public List<T> retornarFaixa( int[] range ) throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select( cq.from( entityClass ) );
        javax.persistence.Query q = getEntityManager().createQuery( cq );
        q.setMaxResults( range[1] - range[0] );
        q.setFirstResult( range[0] );
        return q.getResultList();
    }

    public int contar() throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from( entityClass );
        cq.select( getEntityManager().getCriteriaBuilder().count( rt ) );
        javax.persistence.Query q = getEntityManager().createQuery( cq );
        return ( (Long) q.getSingleResult() ).intValue();
    }
}
