/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import modelo.acesso.Usuario;

/**
 *
 * @author eugenio
 */
public interface UsuarioServico {

    Usuario efetuarLogin( Usuario usuario );
}
