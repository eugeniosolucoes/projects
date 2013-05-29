/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.transporte.impl;

import br.com.sanger.modelo.transporte.automovel.TransporteAutomovel;
import br.com.sanger.repositorio.AbstractRepository;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author eugenio
 */
public class TransporteAutomovelRepository extends AbstractRepository<TransporteAutomovel> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransporteAutomovelRepository() {
        super( TransporteAutomovel.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
    
    public TransporteAutomovelRepository(Class entityClass) {
        super( entityClass );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
    
}
