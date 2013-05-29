/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.apoio.impl;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.repositorio.apoio.impl.EnderecoRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class EnderecoService extends GenericService<Endereco> {

    public EnderecoService() {
        super( new EnderecoRepository() );
    }

    @Override
    public void salvar( Endereco obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( Endereco obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( Endereco obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getLogradouro() ) ) {
            throw new Exception( "O endereço dever ser informado!" );
        }
    }
}
