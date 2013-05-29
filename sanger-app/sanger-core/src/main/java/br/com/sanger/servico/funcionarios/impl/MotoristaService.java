/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.funcionarios.impl;

import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.repositorio.funcionarios.impl.MotoristaRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class MotoristaService extends GenericService<Motorista> {
    
    public MotoristaService() {
        super( new MotoristaRepository() );
    }
    
    @Override
    public void salvar( Motorista obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }
    
    @Override
    public void editar( Motorista obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }
    
    @Override
    public void validacao( Motorista obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getCpf() ) ) {
            obj.setCpf( null );
        }
        if ( MyStrings.isNullOrEmpty( obj.getIdentidade() ) ) {
            obj.setIdentidade( null );
        }        
        if ( MyStrings.isNullOrEmpty( obj.getNome() ) ) {
            throw new Exception( "O nome dever ser informado!" );
        }
    }
}
