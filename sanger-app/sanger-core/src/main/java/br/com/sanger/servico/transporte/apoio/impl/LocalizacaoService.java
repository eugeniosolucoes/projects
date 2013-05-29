/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.transporte.apoio.impl;

import br.com.sanger.modelo.transporte.apoio.Localizacao;
import br.com.sanger.repositorio.transporte.apoio.impl.LocalizacaoRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class LocalizacaoService extends GenericService<Localizacao> {

    public LocalizacaoService() {
        super( new LocalizacaoRepository() );
    }

    @Override
    public void salvar( Localizacao obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( Localizacao obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( Localizacao obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getDescricao() ) ) {
            throw new Exception( "A descrição dever ser informada!" );
        }
    }
}
