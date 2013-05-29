/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.servico.seguranca.impl;

import java.util.List;

import br.com.sanger.modelo.seguranca.Perfil;
import br.com.sanger.modelo.seguranca.Usuario;
import br.com.sanger.repositorio.seguranca.impl.UsuarioRepository;
import br.com.sanger.servico.GenericService;
import br.com.sanger.servico.excecoes.ServicoException;
import br.com.sanger.util.MyStrings;

/**
 *
 * @author eugenio
 */
public class UsuarioService extends GenericService<Usuario> {

    public UsuarioService() {
        super( new UsuarioRepository() );
    }

    @Override
    public void salvar( Usuario obj ) throws Exception {
        try {
            super.salvar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void editar( Usuario obj ) throws Exception {
        try {
            super.editar( obj );
        } catch ( Exception e ) {
            throw new ServicoException( e );
        }
    }

    @Override
    public void validacao( Usuario obj ) throws Exception {
        if ( obj == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getLogin() ) ) {
            throw new Exception( "O login dever ser informado!" );
        }
        if ( MyStrings.isNullOrEmpty( obj.getNome() ) ) {
            throw new Exception( "O nome dever ser informado!" );
        }
    }

    public List<Usuario> usuariosNaoAdicionados( Perfil perfil ) throws Exception {
        if ( perfil == null ) {
            throw new IllegalStateException( "Objeto nulo!" );
        }
        return ( (UsuarioRepository) dao ).usuariosNaoAdicionados( perfil );
    }
}
