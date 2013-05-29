/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.impl;

import br.com.sanger.modelo.transporte.automovel.TransporteAutomovel;
import br.com.sanger.repositorio.transporte.impl.TransporteAutomovelRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;

/**
 *
 * @author eugenio
 */
public class TransporteAutomovelService extends GenericService<TransporteAutomovel> {

    public TransporteAutomovelService() {
        super( new TransporteAutomovelRepository() );
    }

    @Override
    public void salvar( TransporteAutomovel obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( TransporteAutomovel obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( TransporteAutomovel obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
    }
}
