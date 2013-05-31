/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.impl;

import br.com.sanger.modelo.transporte.Destinatario;
import br.com.sanger.repositorio.transporte.impl.DestinatarioRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;

/**
 *
 * @author eugenio
 */
public class DestinatarioService extends GenericService<Destinatario> {

    public DestinatarioService() {
        super( new DestinatarioRepository() );
    }

    @Override
    public void salvar( Destinatario obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( Destinatario obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( Destinatario obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
    }
}
