/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.repositorio.clientes.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.sanger.modelo.clientes.ClientePessoaFisica;
import br.com.sanger.repositorio.AbstractRepository;

/**
 *
 * @author eugenio
 */
public class ClientePessoaFisicaRepository extends AbstractRepository<ClientePessoaFisica> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientePessoaFisicaRepository() {
        super( ClientePessoaFisica.class );
        factory = Persistence.createEntityManagerFactory( AbstractRepository.PERSISTENCE_UNIT_NAME );
        em = factory.createEntityManager();
    }
}
