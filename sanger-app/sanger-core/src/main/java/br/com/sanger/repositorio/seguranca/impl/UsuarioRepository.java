/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.seguranca.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.sanger.modelo.seguranca.Perfil;
import br.com.sanger.modelo.seguranca.Usuario;
import br.com.sanger.repositorio.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class UsuarioRepository extends AbstractRepository<Usuario> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioRepository() {
        super( Usuario.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }

    public List<Usuario> usuariosNaoAdicionados( Perfil perfil ) throws Exception {
        Query query = em.createQuery( "SELECT "
                + "DISTINCT u FROM Usuario u "
                + "WHERE u.id NOT IN ( SELECT u2.id FROM Usuario u2 JOIN u2.perfis p WHERE p.id = ?1)" );
        query.setParameter( 1, perfil.getId() );
        return query.getResultList();
    }
}
