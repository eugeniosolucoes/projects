/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.util;

import br.com.sanger.modelo.seguranca.Direito;
import br.com.sanger.modelo.seguranca.Perfil;
import br.com.sanger.modelo.seguranca.Usuario;
import br.com.sanger.repositorio.IRepository;
import br.com.sanger.repositorio.seguranca.impl.UsuarioRepository;

/**
 *
 * @author eugenio
 */
public class CriarBanco {

    private static IRepository repositorio;

    public static void main( String[] args ) throws Exception {

        repositorio = new UsuarioRepository();

        Perfil gerente = new Perfil( "GERENTE" );

        Direito direito = new Direito( "CRIAR FUNCIONARIO" );

        Usuario usuario = new Usuario( "Eugenio", "123" );

        usuario.setNome( "eugenio" );

        repositorio.criar( usuario );

        usuario.getPerfis().add( gerente );

        gerente.getDireitos().add( direito );

        repositorio.editar( usuario );

    }
}
