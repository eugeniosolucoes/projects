/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.funcionarios.impl;

import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.repositorio.funcionarios.impl.AutonomoRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class AutonomoService extends GenericService<Autonomo> {
    
    public AutonomoService() {
        super( new AutonomoRepository() );
    }
    
    @Override
    public void salvar( Autonomo obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }
    
    @Override
    public void editar( Autonomo obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }
    
    @Override
    public void validacao( Autonomo obj ) throws Exception {
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
