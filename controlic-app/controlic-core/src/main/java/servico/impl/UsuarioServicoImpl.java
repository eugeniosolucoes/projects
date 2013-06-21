/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.impl;

import modelo.acesso.Usuario;
import repositorio.MilitarDAO;
import servico.UsuarioServico;

/**
 *
 * @author eugenio
 */
public class UsuarioServicoImpl implements UsuarioServico {

    private MilitarDAO dao;

    public UsuarioServicoImpl( ) {
        dao = new MilitarDAO();
    }

    @Override
    public Usuario efetuarLogin( Usuario usuario ) {
        if ( usuario == null ) {
            throw new NullPointerException( "O usuário deve ser informado!" );
        }
        return dao.retornarPorNip( usuario.getLogin() );
    }
    
}
