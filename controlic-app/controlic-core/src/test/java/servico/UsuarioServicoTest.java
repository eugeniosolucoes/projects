/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import servico.impl.UsuarioServicoImpl;
import modelo.jpa.Praca;
import modelo.acesso.Usuario;
import org.junit.Test;
import repositorio.MilitarDAO;

/**
 *
 * @author eugenio
 */
public class UsuarioServicoTest {

    public UsuarioServicoTest() {
    }

    //@Test
    public void testEfetuarLogin() {
        System.out.println( "efetuarLogin" );
        Usuario usuario = new Praca( "05866243" );
        usuario.setSenha( "senha" );
        UsuarioServico instance = new UsuarioServicoImpl();
        Usuario result = instance.efetuarLogin( usuario );
        
        System.out.println(result.getNome());
    }
}
