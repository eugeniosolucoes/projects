/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.repositorio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cpo-202
 */
public abstract class AbstractRepository<T>  {

    protected EntityManagerFactory factory;

    protected EntityManager em;

    public static final String PERSISTENCE_UNIT_NAME = "JQ_DATATABLE_PU";

    private Class<T> entityClass;

    public AbstractRepository( Class<T> entityClass ) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void criar( T entity ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist( entity );
        getEntityManager().getTransaction().commit();
    }

    public void editar( T entity ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge( entity );
        getEntityManager().getTransaction().commit();
    }

    public void excluir( T entity ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove( getEntityManager().merge( entity ) );
        getEntityManager().getTransaction().commit();
    }

    public T retornar( Object id ) {
        getEntityManager().clear();
        return getEntityManager().find( entityClass, id );
    }

    public List<T> listar() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select( cq.from( entityClass ) );
        return getEntityManager().createQuery( cq ).getResultList();
    }

    public List<T> retornarFaixa( int[] range ) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select( cq.from( entityClass ) );
        javax.persistence.Query q = getEntityManager().createQuery( cq );
        q.setMaxResults( range[1] - range[0] );
        q.setFirstResult( range[0] );
        return q.getResultList();
    }

    public int contar() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from( entityClass );
        cq.select( getEntityManager().getCriteriaBuilder().count( rt ) );
        javax.persistence.Query q = getEntityManager().createQuery( cq );
        return ( (Long) q.getSingleResult() ).intValue();
    }
}
