/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.clientes.impl;

import br.com.sanger.modelo.clientes.ClientePessoaFisica;
import br.com.sanger.repositorio.clientes.impl.ClientePessoaFisicaRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class ClientePessoaFisicaService extends GenericService<ClientePessoaFisica> {

    public ClientePessoaFisicaService() {
        super( new ClientePessoaFisicaRepository() );
    }

    @Override
    public void salvar( ClientePessoaFisica obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( ClientePessoaFisica obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( ClientePessoaFisica obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getCpf() ) ) {
            obj.setCpf( null );
        }
        if ( MyStrings.isNullOrEmpty( obj.getNome() ) ) {
            throw new Exception( "O nome dever ser informado!" );
        }
    }
}
