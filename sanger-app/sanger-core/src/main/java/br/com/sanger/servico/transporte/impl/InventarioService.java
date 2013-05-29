/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.impl;

import br.com.sanger.modelo.transporte.interestadual.Inventario;
import br.com.sanger.repositorio.transporte.impl.InventarioRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;

/**
 *
 * @author eugenio
 */
public class InventarioService extends GenericService<Inventario> {

    public InventarioService() {
        super( new InventarioRepository() );
    }

    @Override
    public void salvar( Inventario obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( Inventario obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( Inventario obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
    }
}
