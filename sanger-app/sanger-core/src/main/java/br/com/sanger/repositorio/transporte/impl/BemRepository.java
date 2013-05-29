/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.transporte.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.sanger.modelo.transporte.interestadual.Bem;
import br.com.sanger.repositorio.impl.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class BemRepository extends AbstractRepository<Bem> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BemRepository() {
        super( Bem.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
