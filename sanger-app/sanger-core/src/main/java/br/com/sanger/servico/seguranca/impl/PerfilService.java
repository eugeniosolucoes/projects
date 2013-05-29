/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.seguranca.impl;

import java.util.List;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.seguranca.Direito;
import br.com.sanger.modelo.seguranca.Perfil;
import br.com.sanger.repositorio.seguranca.impl.PerfilRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class PerfilService extends GenericService<Perfil> {

    public PerfilService() {
        super( new PerfilRepository() );
    }

    @Override
    public void validacao( Perfil obj ) throws Exception {
        if ( MyStrings.isNullOrEmpty( obj.getDescricao() ) ) {
            throw new Exception( "A descricao dever ser informada!" );
        }
    }

    public List<Perfil> perfisNaoAdicionados( IEntidade usuario ) throws Exception {
        return ( (PerfilRepository) dao ).perfisNaoAdicionados( usuario );
    }

    public List<Perfil> perfisNaoAdicionados( Direito direito ) throws Exception {
        return ( (PerfilRepository) dao ).perfisNaoAdicionados( direito );
    }
}
