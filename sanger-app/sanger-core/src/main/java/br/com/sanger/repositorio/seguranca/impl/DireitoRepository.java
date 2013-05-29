/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.seguranca.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.sanger.modelo.seguranca.Direito;
import br.com.sanger.modelo.seguranca.Perfil;
import br.com.sanger.repositorio.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class DireitoRepository extends AbstractRepository<Direito> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireitoRepository() {
        super( Direito.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }

    public List<Direito> direitosNaoAdicionados( Perfil perfil ) throws Exception {
        Query query = em.createQuery( "SELECT "
                + "DISTINCT d FROM Direito d "
                + "WHERE d.id NOT IN ( SELECT d2.id FROM Direito d2 JOIN d2.perfis p WHERE p.id = ?1)" );
        query.setParameter( 1, perfil.getId() );
        return query.getResultList();
    }
}
