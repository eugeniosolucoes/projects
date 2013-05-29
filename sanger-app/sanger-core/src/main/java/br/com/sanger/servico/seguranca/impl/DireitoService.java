/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.seguranca.impl;

import java.util.List;

import br.com.sanger.modelo.seguranca.Direito;
import br.com.sanger.modelo.seguranca.Perfil;
import br.com.sanger.repositorio.seguranca.impl.DireitoRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class DireitoService extends GenericService<Direito> {

    public DireitoService() {
        super( new DireitoRepository() );
    }

    @Override
    public void validacao( Direito obj ) throws Exception {
        if ( MyStrings.isNullOrEmpty( obj.getDescricao() ) ) {
            throw new Exception( "A descricao dever ser informada!" );
        }
    }

    public List<Direito> direitosNaoAdicionados( Perfil perfil ) throws Exception {
        return ( (DireitoRepository) dao ).direitosNaoAdicionados( perfil );
    }
}
