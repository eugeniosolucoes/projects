/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.transporte.apoio.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.sanger.modelo.transporte.apoio.Simbolo;
import br.com.sanger.repositorio.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class SimboloRepository extends AbstractRepository<Simbolo> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SimboloRepository() {
        super( Simbolo.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
