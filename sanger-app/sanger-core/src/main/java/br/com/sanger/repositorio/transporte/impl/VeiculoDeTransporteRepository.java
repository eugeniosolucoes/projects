/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.transporte.impl;

import br.com.sanger.modelo.transporte.VeiculoDeTransporte;
import br.com.sanger.repositorio.AbstractRepository;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author eugenio
 */
public class VeiculoDeTransporteRepository extends AbstractRepository<VeiculoDeTransporte> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VeiculoDeTransporteRepository() {
        super( VeiculoDeTransporte.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
