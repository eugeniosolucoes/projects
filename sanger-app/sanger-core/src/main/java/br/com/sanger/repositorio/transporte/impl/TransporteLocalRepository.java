/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.transporte.impl;

import br.com.sanger.modelo.IUsuario;
import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import br.com.sanger.repositorio.AbstractRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author eugenio
 */
public class TransporteLocalRepository extends AbstractRepository<TransporteLocal> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransporteLocalRepository() {
        super( TransporteLocal.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }

    public TransporteLocalRepository( Class entityClass ) {
        super( entityClass );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }

    public List<Autonomo> autonomosNaoAdicionados( TransporteLocal obj ) throws Exception {
        Query query = em.createQuery( "SELECT "
                + "DISTINCT au FROM Autonomo au "
                + "WHERE au.id NOT IN ( SELECT aj.id FROM TransporteLocal tl JOIN tl.ajudantes aj WHERE tl.id = ?1) " );
        query.setParameter( 1, obj.getId() );
        return query.getResultList();
    }
    
 
}
