/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.service;

import br.com.eugeniosolucoes.security.Usuario;
import java.util.List;

/**
 *
 * @author eugenio
 */
public interface UsuarioService {

    public static final int PAGE = 0;

    public static final int SIZE = 5;

    Usuario salvar( Usuario usuario );

    void autenticar( String nome, String senha );

    String getUsuarioLogado();

    Usuario getUsuario( Long id );

    List<Usuario> listar();
}
