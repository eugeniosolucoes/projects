/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.transporte.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.sanger.modelo.transporte.interestadual.Item;
import br.com.sanger.repositorio.impl.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class ItemRepository extends AbstractRepository<Item> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemRepository() {
        super( Item.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
