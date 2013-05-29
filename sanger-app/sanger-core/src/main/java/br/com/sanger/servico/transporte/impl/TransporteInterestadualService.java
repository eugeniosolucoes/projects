/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.impl;

import br.com.sanger.modelo.transporte.interestadual.TransporteInterestadual;
import br.com.sanger.repositorio.transporte.impl.TransporteInterestadualRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;

/**
 *
 * @author eugenio
 */
public class TransporteInterestadualService extends GenericService<TransporteInterestadual> {

    public TransporteInterestadualService() {
        super( new TransporteInterestadualRepository() );
    }

    @Override
    public void salvar( TransporteInterestadual obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( TransporteInterestadual obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( TransporteInterestadual obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
    }
}
