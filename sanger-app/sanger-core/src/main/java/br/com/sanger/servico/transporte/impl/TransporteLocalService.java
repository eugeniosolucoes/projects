/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.impl;

import br.com.sanger.modelo.transporte.local.TransporteLocal;
import br.com.sanger.repositorio.transporte.impl.TransporteLocalRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;

/**
 *
 * @author eugenio
 */
public class TransporteLocalService extends GenericService<TransporteLocal> {

    public TransporteLocalService() {
        super( new TransporteLocalRepository() );
    }

    @Override
    public void salvar( TransporteLocal obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( TransporteLocal obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( TransporteLocal obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
    }
}
