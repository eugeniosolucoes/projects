/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.apoio.impl;

import br.com.sanger.modelo.transporte.apoio.Simbolo;
import br.com.sanger.repositorio.transporte.apoio.impl.SimboloRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class SimboloService extends GenericService<Simbolo> {

    public SimboloService() {
        super( new SimboloRepository() );
    }

    @Override
    public void salvar( Simbolo obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( Simbolo obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( Simbolo obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getSigla() ) ) {
            throw new Exception( "A sigla dever ser informada!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getDescricao() ) ) {
            throw new Exception( "A descrição dever ser informada!" );
        }
    }
}
