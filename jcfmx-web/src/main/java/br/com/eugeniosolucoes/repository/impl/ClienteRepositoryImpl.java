/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.repository.impl;

import br.com.eugeniosolucoes.model.Cliente;
import br.com.eugeniosolucoes.repository.ClienteRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepositoryImpl extends AbstractRepository<Cliente, Long> implements ClienteRepository {

    public ClienteRepositoryImpl() {
        super( Cliente.class );
    }

}
