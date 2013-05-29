/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.util;

import java.util.List;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.seguranca.Perfil;
import br.com.sanger.repositorio.seguranca.impl.PerfilRepository;
import br.com.sanger.servico.seguranca.impl.UsuarioService;

/**
 *
 * @author eugenio
 */
public class TestarPerfil {

    public static void main( String[] args ) throws Exception {
        IEntidade usuario = new UsuarioService().retornar( new Long( 2 ) );

        List<Perfil> perfis = new PerfilRepository().perfisNaoAdicionados( usuario );

        for ( Perfil p : perfis ) {
            System.out.println( p.getDescricao() );
        }

    }
}
