/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.impl;

import br.com.sanger.modelo.transporte.VeiculoDeTransporte;
import br.com.sanger.repositorio.transporte.impl.VeiculoDeTransporteRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;

/**
 *
 * @author eugenio
 */
public class VeiculoDeTransporteService extends GenericService<VeiculoDeTransporte> {

    public VeiculoDeTransporteService() {
        super( new VeiculoDeTransporteRepository() );
    }

    @Override
    public void salvar( VeiculoDeTransporte obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( VeiculoDeTransporte obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( VeiculoDeTransporte obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
    }
}
