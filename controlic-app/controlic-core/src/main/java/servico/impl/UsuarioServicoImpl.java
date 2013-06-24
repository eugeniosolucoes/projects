/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.impl;

import javax.naming.NamingException;
import modelo.acesso.Usuario;
import repositorio.MilitarDAO;
import servico.UsuarioServico;
import util.Ldap;

/**
 *
 * @author eugenio
 */
public class UsuarioServicoImpl implements UsuarioServico {

    private MilitarDAO dao;

    public UsuarioServicoImpl() {
        dao = new MilitarDAO();
    }

    @Override
    public Usuario efetuarLogin( Usuario usuario ) {
        if ( usuario == null ) {
            throw new NullPointerException( "O usuário deve ser informado!" );
        }
        try {
            autenticarLDAP( usuario );
        } catch ( NamingException ex ) {
            throw new IllegalStateException( ex );
        } catch ( Exception ex ) {
            throw new IllegalStateException( ex );
        }
        return dao.retornarPorNip( usuario.getLogin() );
    }

    private void autenticarLDAP( Usuario usuario ) throws NamingException, Exception {
        Ldap ldap = new Ldap( "diretorio.mb", "dc=mb", "cn=squid,dc=mb", "dtm3@@" );
        ldap.login( usuario.getLogin(), usuario.getSenha() );
    }
}
