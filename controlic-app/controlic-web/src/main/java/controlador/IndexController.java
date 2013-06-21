/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import modelo.acesso.Usuario;
import modelo.jpa.Militar;
import modelo.jpa.Praca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import servico.impl.UsuarioServicoImpl;
import util.MyStrings;

/**
 *
 * @author eugenio
 */
@Controller
public class IndexController {
    
    @Autowired
    HttpServletRequest request;
    
    @RequestMapping( "/index" )
    public String retornaPrincipal( Model model ) {
        if ( request.getSession().getAttribute( "usuario" ) == null ) {
            return "login";
        } else {
            return "index";
        }
    }
    
    @RequestMapping( "/logout" )
    public String logout( Model model ) {
        request.getSession().setAttribute( "usuario", null );
        return "login";
    }
    
    @RequestMapping( "/login" )
    public String login( Model model ) {
        try {
            Militar militar = new Praca( request.getParameter( "login" ) );
            militar.setSenha( request.getParameter( "senha" ) );
            Usuario usuario = new UsuarioServicoImpl().efetuarLogin( militar );
            request.getSession().setAttribute( "usuario", usuario );
            return "index";
        } catch ( Exception e ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.WARNING, e.getMessage() );
            model.addAttribute( "tipoMensagem", Mensagem.TYPE_ERROR );
            model.addAttribute( "mensagem", MyStrings.cleanMessage( "Usuário ou Senha inválidos!" ) );
            return "login";
        }
    }
}
