/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.transporte.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.sanger.modelo.transporte.Recibo;
import br.com.sanger.repositorio.impl.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class ReciboRepository extends AbstractRepository<Recibo> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReciboRepository() {
        super( Recibo.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
