/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.seguranca.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.seguranca.Direito;
import br.com.sanger.modelo.seguranca.Perfil;
import br.com.sanger.repositorio.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class PerfilRepository extends AbstractRepository<Perfil> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilRepository() {
        super( Perfil.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }

    public List<Perfil> perfisNaoAdicionados( IEntidade usuario ) throws Exception {
        Query query = em.createQuery( "SELECT "
                + "DISTINCT p FROM Perfil p "
                + "WHERE p.id NOT IN ( SELECT p2.id FROM Perfil p2 JOIN p2.usuarios u WHERE u.id = ?1)" );
        query.setParameter( 1, usuario.getId() );
        return query.getResultList();
    }

    public List<Perfil> perfisNaoAdicionados( Direito direito ) throws Exception {
        Query query = em.createQuery( "SELECT "
                + "DISTINCT p FROM Perfil p "
                + "WHERE p.id NOT IN ( SELECT p2.id FROM Perfil p2 JOIN p2.direitos d WHERE d.id = ?1)" );
        query.setParameter( 1, direito.getId() );
        return query.getResultList();
    }
}
