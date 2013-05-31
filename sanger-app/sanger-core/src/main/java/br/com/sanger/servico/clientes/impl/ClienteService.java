/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.clientes.impl;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.repositorio.clientes.impl.ClienteRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class ClienteService extends GenericService<Cliente> {

    public ClienteService() {
        super( new ClienteRepository() );
    }

    @Override
    public void salvar( Cliente obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( Cliente obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( Cliente obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getNome() ) ) {
            throw new Exception( "O nome dever ser informado!" );
        }
    }
}
