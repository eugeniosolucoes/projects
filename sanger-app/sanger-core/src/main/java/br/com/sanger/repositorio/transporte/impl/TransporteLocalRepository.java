/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.transporte.impl;

import br.com.sanger.modelo.transporte.local.TransporteLocal;
import br.com.sanger.repositorio.AbstractRepository;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

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
    
    public TransporteLocalRepository(Class entityClass) {
        super( entityClass );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
    
}
