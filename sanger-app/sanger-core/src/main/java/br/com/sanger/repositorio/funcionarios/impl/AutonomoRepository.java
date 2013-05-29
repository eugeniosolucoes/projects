/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.funcionarios.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.repositorio.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class AutonomoRepository extends AbstractRepository<Autonomo> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutonomoRepository() {
        super( Autonomo.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
