/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service.impl;

import br.com.eugeniosolucoes.model.Cliente;
import br.com.eugeniosolucoes.model.dto.ClienteDTO;
import br.com.eugeniosolucoes.repository.ClienteRepository;
import br.com.eugeniosolucoes.service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService<ClienteDTO> {

    private final ClienteRepository repository;

    @Autowired
    public ClienteServiceImpl( ClienteRepository repository ) {
        this.repository = repository;
    }

    @Override
    public ClienteDTO salvar( ClienteDTO obj ) {
        Cliente clienteView = obj.builder();
        if ( !obj.isNew() ) {
            Cliente cliente = repository.retornar( clienteView.getId() );
            cliente.setNome( clienteView.getNome() );
            cliente.setEmail( clienteView.getEmail() );
            repository.salvar( cliente );
        } else {
            repository.salvar( clienteView );
            obj.setId( clienteView.getId().toString() );
        }
        return obj;
    }

    @Override
    public void excluir( ClienteDTO obj ) {
        repository.excluir( obj.builder().getId() );
    }

    @Override
    public void excluir( String id ) {
        repository.excluir( Long.valueOf( id ) );
    }

    @Override
    public List<ClienteDTO> listar() {
        List<ClienteDTO> list = new ArrayList<>();
        List<Cliente> clientes = repository.listar();
        for ( Cliente cliente : clientes ) {
            list.add( ClienteDTO.builder( cliente ) );
        }
        return list;
    }

    @Override
    public ClienteDTO buscar( String id ) {
        Cliente cliente = repository.retornar( Long.valueOf( id ) );
        return ClienteDTO.builder( cliente );
    }

}
