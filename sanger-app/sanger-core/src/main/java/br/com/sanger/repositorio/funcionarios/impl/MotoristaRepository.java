/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.funcionarios.impl;

import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.repositorio.AbstractRepository;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author eugenio
 */
public class MotoristaRepository extends AbstractRepository<Motorista> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotoristaRepository() {
        super( Motorista.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
